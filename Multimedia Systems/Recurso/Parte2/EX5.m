close all
clc
clear

entropia = 0.2*log2(0.2) + 0.1*log2(0.1) + 0.6*log2(0.6) + 0.1*log2(0.1);
entropia = -entropia;

probs = [0.2 0.1 0.6 0.1];
ent = Entropia(probs);