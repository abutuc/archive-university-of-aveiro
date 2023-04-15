import {Animal} from "./animal";

class Reptile extends Animal{
    constructor(habitat: string) {
        super(habitat);
    }
    show(): void {
        console.log(`Reptile[habitat: ${this.habitat}]`);
    }
}