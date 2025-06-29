package ua.tqs.lab3_2.data;

public class CarDTO {
    private Long carId;
    private String maker;
    private String model;

    public static CarDTO fromCarEntity(Car car){
        return new CarDTO(car.getCarId(), car.getMaker(), car.getModel());
    }

    public Car toCarEntity(){
        Car car = new Car();
        car.setCarId(getCarId());
        car.setMaker(getMaker());
        car.setModel(getModel());
        return car;
    }

    public CarDTO(Long carID, String maker, String model){
        this.carId = carID;
        this.maker = maker;
        this.model = model;
    }

    public Long getCarId() {
        return carId;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
