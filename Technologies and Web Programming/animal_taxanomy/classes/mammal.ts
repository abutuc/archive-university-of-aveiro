import {Animal} from "./animal";

class Mammal extends Animal {
    static nMammals = 0;
    constructor(habitat: string) {
        super(habitat);
        Mammal.nMammals++;
    }
    show(): void {
        console.log(`Mammal[habitat: ${this.habitat}]`);
    }

    talk(): void {
        console.log("Hi, I'm a Mammal! I'm talking!");
    }
} export { Mammal };