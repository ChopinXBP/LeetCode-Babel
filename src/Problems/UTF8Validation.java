package Problems;

/**
 *
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 * For 1-byte character, the first bit is a 0, followed by its unicode code.
 * For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * 对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
 * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
 *
 */

public class UTF8Validation {
    public boolean validUtf8(int[] data) {
        int[] mask = {0b11000000, 0b10000000, 0b11100000, 0b11110000, 0b11111000};
        int[] code = {0b10000000, 0b00000000, 0b11000000, 0b11100000, 0b11110000};
        int idx = 0;
        while(idx < data.length){
            int type = -1;
            for(int i = 1; i < 5; i++) {
                if((data[idx] & mask[i]) == code[i]){
                    type = i;
                    break;
                }
            }
            if(type == -1 || type > data.length - idx){
                return false;
            }
            while(--type != 0) {
                if((data[++idx] & mask[0]) != code[0]){
                    return false;
                }
            }
            idx++;
        }
        return true;
    }
}
