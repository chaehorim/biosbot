> log.js

fileStr=$(ls -m *.dat)
echo $fileStr

array=($(echo "$fileStr" | tr ',' '\n'))


for element in "${array[@]}"
do
    echo "$element"
    cat "$element" >> log.js
done
