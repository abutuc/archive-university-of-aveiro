import { faker } from '@faker-js/faker';

const Parcels = [...Array(10)].map((_, index) => {
    const status = faker.helpers.arrayElement(['Delivered', 'In Transit', 'Pending']);
    const parcel = {
        parcel_id: faker.number.int(),
        delivery_code: faker.string.alphanumeric(6),
        pickup_code: faker.string.alphanumeric(6),
        weight: faker.number.float(),
        delivery_date: null,
        pickup_date: null,
        status: status,
        store_name: faker.company.name(),
    };

    if (status === 'Pending') {
        parcel.delivery_date = faker.date.past().toString();
    }

    if (status === 'Delivered') {
        parcel.delivery_date = faker.date.past().toString();
        parcel.pickup_date = faker.date.past().toString();
    }

    return parcel;
});


const Parcel = {
    parcel_id: faker.number.int(),
    delivery_code: faker.string.alphanumeric(6),
    pickup_code: faker.string.alphanumeric(6),
    weight: faker.number.float(),
    delivery_date: faker.date.past(),
    pickup_date: faker.date.past(),
    status: faker.helpers.arrayElement(['Delivered', 'In Transit', 'Pending']),
};

export { Parcels, Parcel };