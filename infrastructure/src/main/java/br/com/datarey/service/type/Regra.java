package br.com.datarey.service.type;

import br.com.datarey.service.ItemPesquisa;
import br.com.generic.dao.BaseSearchBuilder;

public enum Regra {
    NAO_E_IGUAL {

        @Override
        public void addSearch(ItemPesquisa itemPesquisa, BaseSearchBuilder<?> baseSearchBuilder) {
            baseSearchBuilder.notEqual(itemPesquisa.getPropriedade(), itemPesquisa.getValue());
        }

    },

    MAIOR_OU_IGUAL {

        @Override
        public void addSearch(ItemPesquisa itemPesquisa, BaseSearchBuilder<?> baseSearchBuilder) {
            baseSearchBuilder.greaterThanOrEqualTo(itemPesquisa.getPropriedade(), (Comparable<?>) itemPesquisa.getValue());
        }
    },

    MENOR_OU_IGUAL {

        @Override
        public void addSearch(ItemPesquisa itemPesquisa, BaseSearchBuilder<?> baseSearchBuilder) {
            baseSearchBuilder.lessThan(itemPesquisa.getPropriedade(), (Comparable<?>) itemPesquisa.getValue());
        }

    },

    EM {

        @Override
        public void addSearch(ItemPesquisa itemPesquisa, BaseSearchBuilder<?> baseSearchBuilder) {
            baseSearchBuilder.in(itemPesquisa.getPropriedade(), itemPesquisa.getValue());
        }

    },

    MAIOR_QUE{

        @Override
        public void addSearch(ItemPesquisa itemPesquisa, BaseSearchBuilder<?> baseSearchBuilder) {
            baseSearchBuilder.greaterThan(itemPesquisa.getPropriedade(), (Comparable<?>) itemPesquisa.getValue());
        }

    },

    MENOR_QUE {

        @Override
        public void addSearch(ItemPesquisa itemPesquisa, BaseSearchBuilder<?> baseSearchBuilder) {
            baseSearchBuilder.lessThan(itemPesquisa.getPropriedade(), (Comparable<?>) itemPesquisa.getValue());
        }

    },

    CONTEM {

        @Override
        public void addSearch(ItemPesquisa itemPesquisa, BaseSearchBuilder<?> baseSearchBuilder) {
            baseSearchBuilder.like(itemPesquisa.getPropriedade(), (String) itemPesquisa.getValue());
        }

    },

    NAO_CONTEM{

        @Override
        public void addSearch(ItemPesquisa itemPesquisa, BaseSearchBuilder<?> baseSearchBuilder) {
            baseSearchBuilder.notLike(itemPesquisa.getPropriedade(), (String) itemPesquisa.getValue());
        }

    },

    IGUAL {

        @Override
        public void addSearch(ItemPesquisa itemPesquisa, BaseSearchBuilder<?> baseSearchBuilder) {
            baseSearchBuilder.equal(itemPesquisa.getPropriedade(), itemPesquisa.getValue());
        }

    };
    
    public abstract void addSearch(ItemPesquisa itemPesquisa, BaseSearchBuilder<?> baseSearchBuilder);
}
