import { faker } from '@faker-js/faker';

const Stats = {
    parcel_limit: faker.number.int(),
    parcels_waiting_for_delivery: faker.number.int(),
    parcels_waiting_for_pickup: faker.number.int(),
    parcels_delivered: faker.number.int(),
};

const ACPStats = [...Array(5)].map((_, index) => ({
    id: faker.number.int(),
    name: faker.company.name(),
    parcel_limit: faker.number.int(),
    parcels_waiting_for_delivery: faker.number.int(),
    parcels_waiting_for_pickup: faker.number.int(),
    parcels_delivered: faker.number.int(),
}));

export { Stats, ACPStats };
