if [ $# -eq 2 ]
then
	lines=$1
	fname=$2
else 
if [ $# -eq 1 ]
then
	fname=$1
	lines=10
fi
fi

terminal=`tty`
exec < $fname
cnt=1

while [ $cnt -le $lines ]
do
	read line
	echo $line
	cnt=`expr $cnt + 1`
done

exec < $terminal
