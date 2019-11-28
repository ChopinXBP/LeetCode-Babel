package Shell;

/**
 *
 * https://leetcode-cn.com/problems/transpose-file/
 *
 */

public class TransposeFile {
}

/*

#!/bin/bash
awk '{
    for(i=1;i<=NF;i++){
        if(NR==1){
                lines[i]=$i
        }else{
                lines[i]=lines[i] " " $i
        }
    }
} END {
    for(i=1; i<=NF; i++){
        print lines[i]
    }
}' file.txt

 */