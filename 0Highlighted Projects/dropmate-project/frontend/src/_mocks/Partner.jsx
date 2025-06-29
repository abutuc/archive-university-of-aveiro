import { faker } from '@faker-js/faker';

const Partners = [...Array(3)].map((_, index) => ({
    id: faker.number.int(),
    name: faker.company.name(),
    email: faker.internet.email(),
    telephone: faker.phone.number(),
    city: faker.location.city(),
    address: faker.location.streetAddress(),
    field: faker.person.jobArea(),
    manager: faker.phone.number(),
    orders_delivered: faker.number.int(),
}));

export { Partners };