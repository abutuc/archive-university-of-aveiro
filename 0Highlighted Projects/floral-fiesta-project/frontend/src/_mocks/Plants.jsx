import { faker } from '@faker-js/faker';

const plants = [...Array(40)].map((plant) => ({
    id: faker.number.int(),
    name: faker.commerce.productName(),
    price: faker.commerce.price(),
    photo: faker.image.url(),
    description: faker.commerce.productDescription(),
    category_id: faker.number.int(),
}));

export { plants }
