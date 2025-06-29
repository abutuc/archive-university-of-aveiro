import { faker } from '@faker-js/faker';

const orders = [...Array(30)].map((order) => {
    const status = faker.helpers.arrayElement(["pending", "ready", "completed"]);
    let deliveryDate = null;
    let pickupDate = null;
  
    if (status === "ready") {
      deliveryDate = faker.date.future().toString();
    } else if (status === "completed") {
      deliveryDate = faker.date.future().toString();
      pickupDate = faker.date.future().toString();
    }
  
    return {
      id: faker.number.int(),
      pickup_code: faker.string.alphanumeric(50),
      description: faker.commerce.productDescription(),
      status: status,
      delivery_date: deliveryDate,
      pickup_date: pickupDate,
    };
  });  

export { orders }