public class ChocolateCakeBuilder implements CakeBuilder {
    private Cake cake;

    public ChocolateCakeBuilder() {}

    public void setCakeShape(Shape shape){cake.setShape(shape);}
    public void addCakeLayer(){
        cake.setNumCakeLayers(cake.getNumCakeLayers()+1);
        if (cake.getNumCakeLayers() >= 2){addCreamLayer();}
    }
    public void addCreamLayer(){cake.setMidLayerCream(Cream.Vanilla);}
    public void addTopLayer(){cake.setTopLayerCream(Cream.Whipped_Cream);}
    public void addMessage(String m) {cake.setMessage(m);}
    public void addTopping() {cake.setTopping(Topping.Fruit);}

    public void createCake() {
        cake = new Cake();
        cake.setType("Soft chocolate");
        addTopLayer(); addTopping();
    }

    public Cake getCake() {return this.cake;}
}