public class SpongeCakeBuilder implements CakeBuilder {
    private Cake cake;

    public SpongeCakeBuilder() {}

    public void setCakeShape(Shape shape){cake.setShape(shape);}
    public void addCakeLayer(){
        cake.setNumCakeLayers(cake.getNumCakeLayers()+1);
        if (cake.getNumCakeLayers() >= 2){addCreamLayer();}
    }
    public void addCreamLayer(){cake.setMidLayerCream(Cream.Red_Berries);}
    public void addTopLayer(){cake.setTopLayerCream(Cream.Whipped_Cream);}
    public void addMessage(String m) {cake.setMessage(m);}
    public void addTopping() {cake.setTopping(Topping.Fruit);}

    public void createCake() {
        cake = new Cake();
        cake.setType("Sponge");
        addTopLayer(); addTopping();
    }

    public Cake getCake() {return this.cake;}
}
