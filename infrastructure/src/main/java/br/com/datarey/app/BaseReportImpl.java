package br.com.datarey.app;

import br.com.datarey.app.exception.ReportException;
import br.com.datarey.dao.BaseDao;
import br.com.datarey.model.Associacao;
import br.com.datarey.model.Componente;
import br.com.datarey.model.Entidade;
import br.com.reportclient.ReportClient;
import br.com.reportclient.socket.ReportClientSocket;
import br.com.serviceinfra.util.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.beanutils.BeanMap;

import javax.enterprise.inject.spi.Bean;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public abstract class BaseReportImpl<D extends BaseDao<?>> implements BaseReport {
	protected D dao;
	protected ReportClient reportClient;
	protected String reportService = "genericReport";
	protected String reportResource;
	protected String resourcesFolder;


    public BaseReportImpl(String reportResource) {
        this.reportResource = reportResource;
		reportClient = new ReportClientSocket();
		reportClient.setHost("localhost");
		reportClient.setPort(4532);
    }

    public BaseReportImpl(String reportService, String reportResource) {
        this.reportService = reportService;
        this.reportResource = reportResource;
    }



	private ResourceBundle getResourceBundle() {
		try {
			return new PropertyResourceBundle(new InputStreamReader(new FileInputStream(resourcesFolder.concat(reportResource).concat(".properties")),
					"UTF-8"));
		} catch (Exception e) {
			return ResourceBundle.getBundle(reportResource);
		}
	}

	private String getResourceString() {
		try {
			return IOUtils.toString(new FileInputStream(resourcesFolder.concat(reportResource).concat(".properties")), "UTF-8");
		} catch (Exception e) {
			try {
				return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(reportResource.concat(".properties")), "UTF-8");
			} catch (Exception e1) {
				return null; 
			}
		}
	}

	@Override
	@SuppressWarnings({ "resource", "unchecked" })
	public List<Map<String, Object>> listarDicionario() {
		if (reportResource != null) {
			InputStream inputStream;
			try {
				inputStream = new FileInputStream(resourcesFolder.concat(reportResource).concat("_dictionary.json"));
			} catch (Exception e) {
				inputStream = getClass().getClassLoader().getResourceAsStream(reportResource.concat("_dictionary.json"));
			}
			
			List<Map<String, Object>> list;  
			try {
				list = (List<Map<String, Object>>) JsonUtil.getObjectMapper().readValue(inputStream, List.class);
			} catch (Exception e) {
				list = new ArrayList<Map<String, Object>>();
			}
			
			ResourceBundle resourceBundle = getResourceBundle();
			for (Map<String, Object> map : list) {
				try {
					map.put("titulo", resourceBundle.getString(map.get("campo").toString()));
				} catch (Exception e) {
					map.put("titulo", map.get("campo").toString());
				}
			}

			return list;
		}
		return reportClient.getApi().listDictionary(reportService);
	}
	
	@Override
	public String gerarRelatorio(List<?> lista, List<Map<String, Object>> metadados) {
		try {
			return reportClient.getApi().executarRelatorio(reportService, 
					"admin",
					listBean2ListMap(lista, metadados != null && !metadados.isEmpty() ? metadados : listarDicionario()), 
					metadados,
					getResourceString());
	    } catch (Throwable t) {
	    	throw new ReportException("Erro ao gerar o relatório!", t);
		}
	}



	private List<?> listBean2ListMap(List<?> lista, List<Map<String, Object>> metadados) {
		try {
			List<Map<String, Object>> listaMap = new ArrayList<Map<String,Object>>(); 
			for(Object entidade: lista) {
				if (metadados != null && !metadados.isEmpty()) {
					listaMap.add(bean2Map(entidade, metadados));
				} else {
					listaMap.add(bean2Map(entidade, 0));
				}
			}
			lista = listaMap;
		} catch (Exception e) {
			throw new RuntimeException("Erro na declaração do dicionário!", e);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> bean2Map(Object entidade, List<Map<String, Object>> metadados) {
		Map<?, ?> itemBean = entidade instanceof Map ? (Map<?, ?>) entidade : new BeanMap(entidade);
		
		Map<String, Object> itemMap = new HashMap<String, Object>(); 
		for(Map<String, Object> metadado: metadados) {
			String campo = metadado.get("campo").toString().replace("-", ".").replace("_", ".");

			Map<?, ?> objBean = itemBean;
			Map<String, Object> objMap = itemMap; 
			
			String ref = "";
			while (campo.contains(".")) {
				String propriedade = campo.substring(0, campo.indexOf("."));

				ref += (ref.isEmpty() ? "" : ".") + propriedade; 

				Object value = objBean.get(propriedade);
				
				if (value != null) {
					if (value instanceof Collection<?>) {
						objMap.put(propriedade, listBean2ListMap(new ArrayList<Object>((Collection<?>) value), getSubMetadados(ref, metadados)));
						break;
					} else {
						Map<String, Object> map = objMap.containsKey(propriedade) ? (HashMap<String, Object>) objMap.get(propriedade) : new HashMap<String, Object>();
						
						objMap.put(propriedade, map);

						objBean = value instanceof Map ? (Map<?, ?>) value : new BeanMap(value);
						objMap = map;
					}
				} else {
					break;
				}

				campo = campo.substring(campo.indexOf(".") + 1);
			}
			
			if (!campo.contains(".")) {
				
				Object value = objBean.get(campo);
				
				if (value != null) {
					objMap.put(campo, value);
				}
			}
		}
		return itemMap;
	}

	private List<Map<String, Object>> getSubMetadados(String ref, List<Map<String, Object>> metadados) {
		List<Map<String, Object>> subMetadados = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> metadado: metadados) {
			String campo = metadado.get("campo").toString().replace("-", ".").replace("_", ".");
			
			if (campo.startsWith(ref)) {
				Map<String, Object> subMetadado = new HashMap<String, Object>();
				
				subMetadado.putAll(metadado);
				subMetadado.put("campo", campo.substring(ref.length() + 1));
				
				subMetadados.add(subMetadado);
			}
		}
		return subMetadados;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> bean2Map(Object bean, int depthLevel) {
		if (bean == null) {
			return null;
		}

		Map<String, Object> itemBean;
		if(bean instanceof Map){
			itemBean = (Map<String, Object>) bean;
		}else{
			itemBean = new HashMap<>();
			BeanMap beanMap = new BeanMap(bean);
			Set<Object> keys = beanMap.keySet();
			for(Object key : keys){
				itemBean.put((String) key, beanMap.get(key));
			}
		}
		Map<String, Object> itemMap = new HashMap<String, Object>();
		
		for(String property : itemBean.keySet()) {
    		Class<?> type = getValueType(itemBean, property);
	    	Object value = null;
	    	
			if (type != null) {
		    	if (Entidade.class.isAssignableFrom(type)) {
					value = bean2Map(itemBean.get(property), depthLevel+1);

		    	} else if (Componente.class.isAssignableFrom(type)) {
			    	value = bean2Map(itemBean.get(property), depthLevel);
		    	} else if (Collection.class.isAssignableFrom(type)) {
					Collection<?> collection = (Collection<?>) itemBean.get(property);
					List<Object> list = new ArrayList<Object>();
					for(Object item: collection) {
						if ((item instanceof Associacao)) {
							list.add(bean2Map(item, depthLevel));
						} else if (item instanceof Entidade) {
							list.add(bean2Map(item, depthLevel+1));
						}
					}
					value = list;

		    	} else {
		    		value = itemBean.get(property);
		    	}
			}
			
			if (value != null) {
				itemMap.put(property, value);
			}
		}
		
		return itemMap;
	}

	private Class<?> getValueType(Object itemBean, String property) {
		Class<?> type = null;
		if (itemBean instanceof BeanMap) {
			type = ((BeanMap) itemBean).getType(property); 
		} else {
			Object value = ((Map<String, Object>)itemBean).get(property);
			if (value != null) {
				type = ((Map<String, Object>)itemBean).get(property).getClass();
			}
		}
		return type;
	}

    public ByteArrayOutputStream exportToPDF(String reportId) {
        return reportClient.getApi().exportToPdf(reportId);
    }

    public ByteArrayOutputStream exportToRTF(String reportId) {
        return reportClient.getApi().exportToRtf(reportId);
    }

    public ByteArrayOutputStream exportToDOCX(String reportId) {
        return reportClient.getApi().exportToDocx(reportId);
    }

    public ByteArrayOutputStream exportToXLSX(String reportId) {
        return reportClient.getApi().exportToXlsx(reportId);
    }

    public ByteArrayOutputStream exportToPPTX(String reportId) {
        return reportClient.getApi().exportToPptx(reportId);
    }

    public ByteArrayOutputStream exportToODT(String reportId) {
        return reportClient.getApi().exportToOdt(reportId);
    }

    public ByteArrayOutputStream exportToODS(String reportId) {
        return reportClient.getApi().exportToOds(reportId);
    }

    public ByteArrayOutputStream exportToHTML(String reportId) {
        return reportClient.getApi().exportToHtml(reportId);
    }

    public ByteArrayOutputStream exportToTXT(String reportId) {
        return reportClient.getApi().exportToTxt(reportId);
    }

    public ByteArrayOutputStream exportToSWF(String reportId) {
        return reportClient.getApi().exportToSwf(reportId);
    }
}