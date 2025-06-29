import { faker } from '@faker-js/faker';

const ACPs = [...Array(5)].map((_, index) => ({
    id: faker.number.int(),
    name: faker.company.name(),
    email: faker.internet.email(),
    telephone: faker.phone.number(),
    city: faker.location.city(),
    address: faker.location.streetAddress(),
    manager: faker.phone.number(),
}));

const ACP = {
    id: faker.number.int(),
    name: faker.company.name(),
    email: faker.internet.email(),
    telephone: faker.phone.number(),
    city: faker.location.city(),
    address: faker.location.streetAddress(),
    manager: faker.phone.number(),
};

export { ACPs, ACP };