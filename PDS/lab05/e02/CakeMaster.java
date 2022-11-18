public class CakeMaster{
    private CakeBuilder cakeBuilder;

    public CakeMaster(){}

    public void setCakeBuilder(CakeBuilder cakeBuilder) {this.cakeBuilder = cakeBuilder;}
    public CakeBuilder getCakeBuilder() {return cakeBuilder;}

    // 3 createCake variations with different parameters
    public void createCake(String message) {
        cakeBuilder.createCake();
        cakeBuilder.addMessage(message);
    }

    public void createCake(int nlayers, String message) {
        cakeBuilder.createCake();
        for (int i = 0; i < nlayers-1; i++){cakeBuilder.addCakeLayer();} // for loop to add nlayers-1 of cake to the cake (the cake has 1 layer as default already)
        cakeBuilder.addMessage(message);

    }

    public void createCake(Shape shape, int nlayers, String message) {
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(shape);
        for (int i = 0; i < nlayers-1; i++){cakeBuilder.addCakeLayer();} // for loop to add nlayers-1 of cake to the cake (the cake has 1 layer as default already)
        cakeBuilder.addMessage(message);
        
    }

    public Cake getCake(){return cakeBuilder.getCake();}
}