package br.com.datarey.test;

import br.com.datarey.dao.BaseDao;
import br.com.datarey.service.BaseService;
import org.junit.Ignore;

import javax.inject.Inject;

@Ignore
public abstract class BaseServiceTest<S extends BaseService<?>> extends BaseTest{

    @Inject
    protected S service;

}
