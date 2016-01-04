package br.com.datarey.transactional;

public class TransactionalReturn {

    private Object value;
    private boolean containsErro;
    private Exception error;

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        containsErro = true;
        this.error = error;
    }

    public boolean containsErro() {
        return containsErro;
    }
    

}
