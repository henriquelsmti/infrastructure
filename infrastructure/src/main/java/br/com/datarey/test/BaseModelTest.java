package br.com.datarey.test;

import junit.framework.TestCase;
import org.junit.Ignore;

import javax.inject.Inject;
import javax.validation.Validator;

@Ignore
public abstract class BaseModelTest extends BaseTest{

    @Inject
    protected Validator validator;

}
