package dataStructure.heap;

public class TestQueue extends TestAbstractQueue{
    private int privateInt;
    int defaultInt;

    public TestQueue(int privateInt, int defaultInt) {
        this.privateInt = privateInt;
        this.defaultInt = defaultInt;
    }

    @Override
    public int testDefault() {
        return this.defaultInt;
    }

    @Override
    public int testPrivate() {
        return this.privateInt;
    }
}
