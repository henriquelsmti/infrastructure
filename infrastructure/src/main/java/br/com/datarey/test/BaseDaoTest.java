package br.com.datarey.test;

import br.com.datarey.dao.BaseDao;
import org.junit.Ignore;

import javax.inject.Inject;
import javax.validation.Validator;

@Ignore
public abstract class BaseDaoTest<D extends BaseDao<?>> extends BaseTest{

    @Inject
    protected D dao;

}
