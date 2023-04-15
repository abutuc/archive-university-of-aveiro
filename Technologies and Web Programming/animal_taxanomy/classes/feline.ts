import {Mammal} from "./mammal";

class Feline extends Mammal {
    static nFelines = 0;
    family: string;
    constructor(habitat: string, family: string) {
        super(habitat);
        this.family = family;
        Feline.nFelines++;
    }
} export { Feline };