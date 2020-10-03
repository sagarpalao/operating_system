flag=1
if [ `expr $# % 2` = 0 ]
then
	for i in $@
	do
		if [ $flag = 1 ]
		then
			s1=`cat $i`
			flag=0
		else
			echo $s1 > $i
			flag=1
		fi
		
	done
else
	echo "no"
fi
