echo "Enter 1st file names"
read f1
echo "Enter 2nd file name"
read f2
s1=`cat $f1`
s2=`cat $f2`
if [ "$s1" = "$s2" ]
then
	rm $f2
	echo "File 2 removed"
fi
