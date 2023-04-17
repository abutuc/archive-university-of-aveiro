%% Clear
clear;
close all;
clc;
% a state transition matrix example
H = [0 1/2 0  1/3  0         % A 
     1/3 0 1/3 0   0     % C
     0 1/2 0   1/3 0        % O
     1/3 0 1/3 0   0       % S
     1/3 0 1/3 1/3 0           % FIM
    ];


 % how to use crawl()
initial=randi([1 4]);
state = crawl(H, initial, 5);

v_i = [0 1/4 0 0 0];
v1 = H*v_i';
v_i2 = [1 0 0 0 0];
v2 = H*v_i2';
v_i3 = [0 0 0 1 0];
v3 = H*v_i3';
v_i4 = [1 0 0 0 0];
v4 = H*v_i4';

prob= 1 * v1(1) * v2(4) * v3(1) * v4(5);
palavra='CASA';
tamanho=length(palavra);

for i=1:10^5
    
end
% Random walk on the Markov chain
% Inputs:
% H - state transition matrix
% first - initial state
% last - terminal or absorving state
function state = crawl(H, first, last)
     % the sequence of states will be saved in the vector "state"
     % initially, the vector contains only the initial state:
     state = [first];
     % keep moving from state to state until state "last" is reached:
     while (1)
          state(end+1) = nextState(H, state(end));
          if (state(end) == last)
              break;
          end
     end
end

% Returning the next state
% Inputs:
% H - state transition matrix
% currentState - current state
function state = nextState(H, currentState)
     % find the probabilities of reaching all states starting at the current one:
     probVector = H(:,currentState)';  % probVector is a row vector 
     n = length(probVector);  %n is the number of states
     % generate the next state randomly according to probabilities probVector:
     state = discrete_rnd(1:n, probVector);
end

% Generate randomly the next state.
% Inputs:
% states = vector with state values
% probVector = probability vector 
function state = discrete_rnd(states, probVector)
     U=rand();
     i = 1 + sum(U > cumsum(probVector));
     state= states(i);
end
