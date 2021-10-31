package Delete;
//分支B2中修改了这里
public class delete {
    /**
     * 删除给定字符串中等号后的字符串
     * @param str 只包含一个等号的字符串
     * @return 原字符串去掉等号后所有字符串的新字符串
     */
    public static String mydelete(String str){
        int i;
        for (i=0;i<str.length();i++){
            if (str.charAt(i)=='='){
                str=str.substring(0,i+1);
                break;
            }
        }
        return str;
    }
    public static String mybackspace(String str){

        for (int i=0;i<str.length();i++){
            if (str.charAt(i)=='='&&i!=str.length()-1){
                return str.substring(0,str.length()-1);
            }
        }
        return str;
    }
}
