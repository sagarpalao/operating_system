for f in *
do
	if [ -f $f -a -w $f -a -r $f -a -x $f ] 
	then
		echo $f
	fi
done
