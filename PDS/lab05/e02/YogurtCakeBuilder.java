public class YogurtCakeBuilder implements CakeBuilder {
    private Cake cake;

    public YogurtCakeBuilder() {}

    public void setCakeShape(Shape shape){cake.setShape(shape);}
    public void addCakeLayer(){
        cake.setNumCakeLayers(cake.getNumCakeLayers()+1);
        if (cake.getNumCakeLayers() >= 2){addCreamLayer();}
    }
    public void addCreamLayer(){cake.setMidLayerCream(Cream.Vanilla);}
    public void addTopLayer(){cake.setTopLayerCream(Cream.Red_Berries);}
    public void addMessage(String m) {cake.setMessage(m);}
    public void addTopping() {cake.setTopping(Topping.Chocolate);}

    public void createCake() {
        cake = new Cake();
        cake.setType("Yogurt");
        addTopLayer(); addTopping();
    }

    public Cake getCake() {return this.cake;}
}
