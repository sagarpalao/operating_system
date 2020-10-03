echo "Enter choice:\n1.Circumference of circle\n2.Factorial\n3.Prilimanry test\n4.Exit"
read ch
case $ch in
	1)echo "Enter radius:"
	  read rad
	  d=$(echo "scale=2;2 * $rad" | bc)
	  cir=$(echo "scale=2;3.14 * d" | bc)
	  echo "circumference: $cir"
	  ;;
	2)echo "Enter number:"
	  read no
	  i=$no
	  fact=1
	  while [ $i -ge 1 ]
	  do
		fact=`echo $fact \* $i|bc`
		i=`expr $i \- 1`
	  done
	  echo "Factorial: $fact"
	  ;;
	3)echo "Enter number:"
	  read no
	  i=1
	  cnt=0
	  while [ $i -le $no ]
	  do
		if [ `expr $no % $i` -eq 0 ]
		then
			cnt=`expr $cnt + 1`
		fi
		i=`expr $i + 1`
	  done
	  if [ $cnt -eq 2 ]
	  then
		echo "prime"
	  else
		echo "not prime"
	  fi
	  ;;
	4)
	  
	  ;;
	
esac