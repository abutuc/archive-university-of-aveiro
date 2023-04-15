import {Feline} from "./feline";

class Cat extends Feline {
    static nCats = 0;
    meow: string;
    name: string;
    constructor(habitat: string, family: string, meow: string, name: string) {
        super(habitat, family);
        this.meow = meow;
        this.name = name;
        Cat.nCats++;
    }

    talk(): void {
        console.log(`${this.name} says ${this.meow}`);
    }
}