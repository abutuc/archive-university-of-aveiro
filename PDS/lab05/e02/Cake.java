public class Cake {
    private Shape shape;
    private String cakeLayer;
    private int numCakeLayers;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;
    private String type;

    public Cake() {this.shape = Shape.Circle; this.numCakeLayers = 1;}
    public void setCakeLayer(String cakeLayer) {this.cakeLayer = cakeLayer;}
    public void setMessage(String message) {this.message = message; }
    public void setMidLayerCream(Cream midLayerCream) {this.midLayerCream = midLayerCream;}
    public void setNumCakeLayers(int numCakeLayers) {this.numCakeLayers = numCakeLayers;}
    public void setShape(Shape shape) {this.shape = shape;}
    public void setTopLayerCream(Cream topLayerCream) {this.topLayerCream = topLayerCream;}
    public void setTopping(Topping topping) {this.topping = topping;}
    public void setType(String type){this.type = type;}

    public String getCakeLayer() {return cakeLayer;}
    public String getMessage() {return message;}
    public Cream getMidLayerCream() {return midLayerCream;}
    public int getNumCakeLayers() {return numCakeLayers;}
    public Shape getShape() {return shape;}
    public Cream getTopLayerCream() {return topLayerCream;}
    public Topping getTopping() {return topping;}
    public String getType(){return type;}
    

    @Override
    public String toString() {
        String str;
        if (midLayerCream == null){str = type + " cake with " + numCakeLayers + " layers, topped with " + topLayerCream + " cream and " + topping + ". Message says: \"" + message + "\".";}
        else {str = type + " cake with " + numCakeLayers + " layers and " + midLayerCream + " cream, topped with " + topLayerCream + " cream and " + topping + ". Message says: \"" + message + "\".";}
        return str;
    }
}