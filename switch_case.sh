echo "Enter character"
read c

case $c in 
	[a-z]) echo "lowercase";;
	[A-Z]) echo "uppercase";;
	[0-9]) echo "integer";;
	*) echo "special character";;
esac
