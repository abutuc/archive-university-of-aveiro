clc
clear
close all

first = 'Hello';
second = {'hello', 'world', 'from', 'me'};

third(1,1) = {'happy'}; % Cell indexing
third{2,1} = 'birthday';    % Content addressing
third{3,1} = 40;
