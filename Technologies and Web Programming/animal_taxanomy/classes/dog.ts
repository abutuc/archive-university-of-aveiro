import {Canine} from "./canine";

class Dog extends Canine {
    static nDogs = 0;
    bark: string;
    constructor(habitat: string, race: string, bark: string) {
        super(habitat, race);
        this.bark = bark;
        Dog.nDogs++;
    }

    talk(): void {
        console.log(this.bark);
    }
} export { Dog };