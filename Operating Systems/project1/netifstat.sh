#!/bin/bash

# array que contém as interfaces
interfaces=()

# for loop que retira do output do comando ifconfig os nomes das interfaces
for interface in $(ifconfig | cut -d ' ' -f1| tr ':' '\n')
do    
    interfaces+=("$interface")
done

# teste para verificar se o número de argumentos passados não excede o máximo (9) nem é inferior ao minimo (1)
if [[ $# -lt 1 ]] || [[ $# -gt 9 ]];
then
	echo "Error: Invalid number of arguments. (Min:1; Max:9)"
	exit 1
fi

# teste para verificar, caso só um argumento seja passado, que seja um inteiro pois serão os segundos utilizadas nas taxas
if [[ $# -eq 1 ]];
then
	if [[  $1 -eq 0 ]];
	then
		echo "Error: time argument must not be equal to 0."
		exit 1
	fi
	
	if ! [[  $1 =~ ^[0-9]+$ ]]; # regex expression avalia se $1 é inteiro
	then
		echo "Error: When passing only 1 argument make sure that it is an integer."
		exit 1
	fi
fi
					#############
# trecho de código que trata do processamento dos argumentos
regex=0
c_flag=0
c_count=0
byte=0
b_count=0
kbyte=0
k_count=0
mbyte=0
m_count=0
p_flag=0
p_count=0
n_interfaces_display=${#interfaces[@]}
sort_TX=0
t_count=0
sort_RX=0
r_count=0
sort_TRATE=0
T_count=0
sort_RRATE=0
R_count=0
reverse=0
v_count=0
loop=0
l_count=0
active_flags=0
last_processed=0
while getopts ":c: :b :k :m :p: :t :r :T :R :v :l " flag;
do
	case "${flag}" in
		c) regex=${OPTARG}
		   c_flag=1
		   active_flags=`echo "scale=1; $active_flags + 1"| bc `
		   last_processed=1
		   c_count=`echo "scale=1; $c_count + 1"| bc `
		;;
		b) byte=1
			if [[ $kbyte -eq 1 ]] || [[ $mbyte -eq 1 ]]; 
			then
				echo "Error: Two or more conversion flags were used."
				exit 1
			fi
			active_flags=`echo "scale=1; $active_flags + 1"| bc `
			last_processed=0
			b_count=`echo "scale=1; $b_count + 1"| bc `
		;;
		k) kbyte=1
			if [[ $byte -eq 1 ]] || [[ $mbyte -eq 1 ]]; 
			then
				echo "Error: Two or more conversion flags were used."
				exit 1
			fi
			active_flags=`echo "scale=1; $active_flags + 1"| bc `
			last_processed=0
			k_count=`echo "scale=1; $k_count + 1"| bc `
		;;
		m) mbyte=1
			if [[ $kbyte -eq 1 ]] || [[ $byte -eq 1 ]]; 
			then
				echo "Error: Two or more conversion flags were used."
				exit 1
			fi
			active_flags=`echo "scale=1; $active_flags + 1"| bc `
			last_processed=0
			m_count=`echo "scale=1; $m_count + 1"| bc `
		;;
		p) n_interfaces_display=${OPTARG}
			p_flag=1
			if ! [[ "$n_interfaces_display" =~ ^[0-9]+$ ]];
			then
				echo "Error: Number of interfaces must be an integer."
				exit 1
			fi
			if [[ $n_interfaces_display -gt ${#interfaces[@]} ]];
			then
				printf "Error: Number of interfaces must be lower or equal than %d.\n" ${#interfaces[@]}
				exit 1
			fi
			active_flags=`echo "scale=1; $active_flags + 1"| bc `
			last_processed=1
			p_count=`echo "scale=1; $p_count + 1"| bc `
		;;
			
		t) sort_TX=1
			if [[ $sort_RX -eq 1 ]] || [[ $sort_TRATE -eq 1 ]] || [[ $sort_RRATE -eq 1 ]]; 
			then
				echo "Error: Two or more sorting flags were used."
				exit 1
			fi
			active_flags=`echo "scale=1; $active_flags + 1"| bc `
			last_processed=0
			t_count=`echo "scale=1; $t_count + 1"| bc `
		;;
		r) sort_RX=1
			if [[ $sort_TX -eq 1 ]] || [[ $sort_TRATE -eq 1 ]] || [[ $sort_RRATE -eq 1 ]]; 
			then
				echo "Error: Two or more sorting flags were used."
				exit 1
			fi
			active_flags=`echo "scale=1; $active_flags + 1"| bc `
			last_processed=0
			r_count=`echo "scale=1; $r_count + 1"| bc `
		;;
		T) sort_TRATE=1
			if [[ $sort_TX -eq 1 ]] || [[ $sort_RX -eq 1 ]] || [[ $sort_RRATE -eq 1 ]]; 
			then
				echo "Error: Two or more sorting flags were used."
				exit 1
			fi
			active_flags=`echo "scale=1; $active_flags + 1"| bc `
			last_processed=0
			T_count=`echo "scale=1; $T_count + 1"| bc `
		;;
		R) sort_RRATE=1
			if [[ $sort_TX -eq 1 ]] || [[ $sort_RX -eq 1 ]] || [[ $sort_TRATE -eq 1 ]]; 
			then
				echo "Error: Two or more sorting flags were used."
				exit 1
			fi
			active_flags=`echo "scale=1; $active_flags + 1"| bc `
			last_processed=0
			R_count=`echo "scale=1; $R_count + 1"| bc `
		;;
		v) reverse=1
		   active_flags=`echo "scale=1; $active_flags + 1"| bc `
		   last_processed=0
		   v_count=`echo "scale=1; $v_count + 1"| bc `
		;;
		l) loop=1
		   active_flags=`echo "scale=1; $active_flags + 1"| bc `
		   last_processed=0
		   l_count=`echo "scale=1; $l_count + 1"| bc `
		;;
		\?) echo "Error: Invalid flag was passed." 
		    exit 1 
		;;
	esac
done

if [[ $c_count -gt 1 ]] || [[ $b_count -gt 1 ]] || [[ $k_count -gt 1 ]] || [[ $m_count -gt 1 ]] || [[ $p_count -gt 1 ]] || [[ $t_count -gt 1 ]] || [[ $r_count -gt 1 ]] || [[ $T_count -gt 1 ]] || [[ $R_count -gt 1 ]] || [[ $v_count -gt 1 ]] || [[ $l_count -gt 1 ]];
then
	echo "Error: Same flag passed more than once."
	exit 1
fi
# Checks if an integer or char was passed in an invalid way, after a flag that doesnt need argument or before a flag.
left_arg=`echo "scale=1; $# - 1"| bc `
if [[ $OPTIND -lt $left_arg ]] && [[ $last_processed -eq 1 ]];
then
	echo "Error: Arguments passed are creating conflict."
	exit 1
fi

if [[ $OPTIND -ne $# ]] && [[ $last_processed -eq 0 ]];
then
	echo "Error: Arguments passed are creating conflict."
	exit 1
fi

# Counts numbers of integers passed as argument and checks if a char was passed in a wrong way
int_arguments=0
for ((i=0; i<=$#; i++))
do
	if  [[ ${!i} =~ ^[0-9]+$ ]];
	then 
		
		int_arguments=`echo "scale=1; $int_arguments + 1"| bc `
	
	elif [[ ${!i} =~ ^[a-zA-Z][^-]* ]];
	then
		new_i=`echo "scale=1; $i - 1"| bc `
		if [[ ${!new_i} =~ "-c" ]];
		then
			continue
		fi
		echo "Error: If not succeding '-c', characters are not permited as arguments."
		exit 1
	fi
		
done

command_line_args=("$@")

if [[ $# -ne 1 ]]; 
then second_to_last_argument=${command_line_args[-2]} 
fi
if [[ $second_to_last_argument =~ "-p" ]] || [[ $second_to_last_argument =~ "-c" ]];
then
	echo "Error: flag argument is creating conflict with rate time."
	exit 1
fi

# test when user passes multiple integers arguments, such as ./netifstat.sh -p 2 2 2
possible_ints_flags=`echo "scale=1; $c_flag + $p_flag + 1"| bc `
if [[ $int_arguments -gt $possible_ints_flags ]];
then
	echo "Error: Invalid number of integers according to active flags."
	exit 1
fi


time=${@: -1}

if [[ $time -eq 0 ]];
then
	echo "Error: time argument must not be zero."
	exit 1
fi

if ! [[ "$time" =~ ^[0-9]+$ ]];
then				
	echo "Error: time argument must be an integer."
	exit 1
fi

# array para os rx_bytes iniciais
rx_bytes_i=()
# array para os tx_bytes iniciais
tx_bytes_i=()
# array para os rx_bytes finais(dps do sleep)
rx_bytes_f=()
#array para os tx_bytes finais(dps do sleep)
tx_bytes_f=()
#array para os rx_bytes recebidos no tempo de sleep
rx_bytes=()
#array para os tx_bytes recebidos no tempo de sleep
tx_bytes=()
#array para a rrate (rrate=rx/tempo)
rrate=()
#array para a trate (trate=tx/tempo)
trate=()
# array para a ordenaçao
ordem=()
# array para auxiliar o reverse
temp_ordem=()
# array que faz stores dos valores totais no modo loop
total_rx_bytes=()
total_tx_bytes=()

# loop que inicializa todos os arrays
for ((f = 0; f < ${#interfaces[@]}; f++));
do
    rx_bytes_i[f]=0
    tx_bytes_i[f]=0
    rx_bytes_f[f]=0
    tx_bytes_f[f]=0
    rx_bytes[f]=0
    tx_bytes[f]=0
    rrate[f]=0
    trate[f]=0
    ordem[f]=$f
    temp_ordem[f]=0
    total_rx_bytes[f]=0
    total_tx_bytes[f]=0
done
			# (end of arguments processing and inicialization)
					#############
x=1
count=0
while [ $x -eq 1 ]
do					
	# for loop que vai buscar os dados de cada interface
	for ((i = 0; i < ${#interfaces[@]}; i++)); 
	do    

    	rx_bytes_i[i]=$(cat /sys/class/net/${interfaces[i]}/statistics/rx_bytes)
    	tx_bytes_i[i]=$(cat /sys/class/net/${interfaces[i]}/statistics/tx_bytes)

	done


	sleep $time
	# for loop que vai buscar os dados de cada interface
	for ((i = 0; i < ${#interfaces[@]}; i++)); 
	do    

    		rx_bytes_f[i]=$(cat /sys/class/net/${interfaces[i]}/statistics/rx_bytes)
    		tx_bytes_f[i]=$(cat /sys/class/net/${interfaces[i]}/statistics/tx_bytes)

	done

	for ((i = 0; i < ${#interfaces[@]}; i++));
	do
    
    		rx_bytes[i]=$((${rx_bytes_f[i]}-${rx_bytes_i[i]}))
    		tx_bytes[i]=$((${tx_bytes_f[i]}-${tx_bytes_i[i]}))
    		if ! [[ $byte -eq 1 || $kbyte -eq 1 || $mbyte -eq 1 ]];
    		then
    			total_tx_bytes[i]=`echo "scale=1; ${total_tx_bytes[i]} + ${tx_bytes[i]}" | bc`
    			total_rx_bytes[i]=`echo "scale=1; ${total_rx_bytes[i]} + ${rx_bytes[i]}" | bc`
    		fi
	done
	
	
	# Conversion into either bytes, kbytes or mbytes
	for ((i = 0; i < ${#interfaces[@]}; i++));
	do
	if [[ $byte -eq 1 ]]; 
	then
		tx_bytes[i]=${tx_bytes[i]}
		rx_bytes[i]=${rx_bytes[i]}
		total_tx_bytes[i]=`echo "scale=1; ${total_tx_bytes[i]} + ${tx_bytes[i]}" | bc`
    		total_rx_bytes[i]=`echo "scale=1; ${total_rx_bytes[i]} + ${rx_bytes[i]}" | bc`
		
	elif [[ $kbyte -eq 1 ]];
	then
		tx_bytes[i]=`echo "scale=1; ${tx_bytes[i]} / 1000" | bc`
		rx_bytes[i]=`echo "scale=1; ${rx_bytes[i]} / 1000" | bc`
		total_tx_bytes[i]=`echo "scale=1; ${total_tx_bytes[i]} + ${tx_bytes[i]}" | bc`
    		total_rx_bytes[i]=`echo "scale=1; ${total_rx_bytes[i]} + ${rx_bytes[i]}" | bc`

	elif [[ $mbyte -eq 1 ]];
	then
		tx_bytes[i]=`echo "scale=1; ${tx_bytes[i]} / 1000000" | bc`
		rx_bytes[i]=`echo "scale=1; ${rx_bytes[i]} / 1000000" | bc`
		total_tx_bytes[i]=`echo "scale=1; ${total_tx_bytes[i]} + ${tx_bytes[i]}" | bc`
    		total_rx_bytes[i]=`echo "scale=1; ${total_rx_bytes[i]} + ${rx_bytes[i]}" | bc`
	fi
	done

	
	
	for ((i = 0; i < ${#interfaces[@]}; i++));
	do
    
    		rrate[i]=`echo "scale=1; ${rx_bytes[i]} / $time" | bc`
    		trate[i]=`echo "scale=1; ${tx_bytes[i]} / $time" | bc`
	done


	# Ordering according to ordering arguments
					#########
	did_order_change=0
	if [ $sort_TX -eq 1 ];
	then
		for ((f = 0; f < ${#interfaces[@]}-1; f++)); do
			for ((i = 0; i < ${#interfaces[@]}-1; i++)); do
				if (( `echo "scale=1; ${tx_bytes[${ordem[i+1]}]} > ${tx_bytes[${ordem[i]}]}" | bc` )); then
					temp=${ordem[i+1]}
					ordem[i+1]=${ordem[i]}
					ordem[i]=$temp 
					did_order_change=1
				fi
			done
		done

	elif [ $sort_RX -eq 1 ];
	then
		for ((f = 0; f < ${#interfaces[@]}-1; f++)); do
			for ((i = 0; i < ${#interfaces[@]}-1; i++)); do
				if (( `echo "scale=1; ${rx_bytes[${ordem[i+1]}]} > ${rx_bytes[${ordem[i]}]}" | bc` )); then
					temp=${ordem[i+1]}
					ordem[i+1]=${ordem[i]}
					ordem[i]=$temp
					did_order_change=1
				fi
			done
		done

	elif [ $sort_TRATE -eq 1 ];
	then
		for ((f = 0; f < ${#interfaces[@]}-1; f++)); do
			for ((i = 0; i < ${#interfaces[@]}-1; i++)); do
				if (( `echo "${trate[${ordem[i+1]}]} > ${trate[${ordem[i]}]}" | bc` )); then
					temp=${ordem[i+1]}
					ordem[i+1]=${ordem[i]}
					ordem[i]=$temp 
					did_order_change=1
				fi
			done
		done

	elif [ $sort_RRATE -eq 1 ];
	then
		for ((f = 0; f < ${#interfaces[@]}-1; f++)); do
			for ((i = 0; i < ${#interfaces[@]}-1; i++)); do
				if (( `echo "${rrate[${ordem[i+1]}]} > ${rrate[${ordem[i]}]}" | bc` )); then
					temp=${ordem[i+1]}
					ordem[i+1]=${ordem[i]}
					ordem[i]=$temp 
					did_order_change=1
				fi
			done
		done
	fi


	# reverses the order 
	if [[ $reverse -eq 1 && $did_order_change -eq 1 ]];
	then
	
		for ((f = 0; f < ${#ordem[@]}; f++)); do
			temp_ordem[f]=${ordem[ ${#ordem[@]} - 1 - $f]}
		done
	
		for ((f = 0; f < ${#ordem[@]}; f++)); do
			ordem[f]=${temp_ordem[$f]}
		done
	fi
					########
	
	if [[ $loop -eq 0 ]]; then
		printf "%-15s %-10s %-10s %-10s %-10s\n" NETIF TX RX TRATE RRATE
		for ((i = 0; i < n_interfaces_display; i++));
		do
    			ord=${ordem[i]}
	    		if [[ $regex =~ 0 ]]; then 
	   			printf "%-15s %-10s %-10s %-10s %-10s\n" ${interfaces[ord]} ${tx_bytes[ord]} ${rx_bytes[ord]} ${trate[ord]} ${rrate[ord]}
		
	    		else
				if [[ ${interfaces[ord]} =~ $regex ]]; then
		    			printf "%-15s %-10s %-10s %-10s %-10s\n" ${interfaces[ord]} ${tx_bytes[ord]} ${rx_bytes[ord]} ${trate[ord]} ${rrate[ord]}
				fi 
	    		fi 	
		done
		break
	else
		if [[ $count -eq 0 ]]; then
			printf "%-15s %-10s %-10s %-10s %-10s %-10s %-10s\n" NETIF TX RX TRATE RRATE TXTOT RXTOT
			count=1
		fi
		for ((i = 0; i < n_interfaces_display; i++));
		do
    			ord=${ordem[i]}
    			if [[ $regex =~ 0 ]]; then 
	   			printf "%-15s %-10s %-10s %-10s %-10s %-10s %-10s\n" ${interfaces[ord]} ${tx_bytes[ord]} ${rx_bytes[ord]} ${trate[ord]} ${rrate[ord]} ${total_tx_bytes[ord]} ${total_rx_bytes[ord]}
		
	    		else
				if [[ ${interfaces[ord]} =~ $regex ]]; then
		    			printf "%-15s %-10s %-10s %-10s %-10s %-10s %-10s\n" ${interfaces[ord]} ${tx_bytes[ord]} ${rx_bytes[ord]} ${trate[ord]} ${rrate[ord]} ${total_tx_bytes[ord]} ${total_rx_bytes[ord]}
				fi 
	    		fi 	
		done
		
	fi
	printf "\n\n"
done
