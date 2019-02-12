/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trn_volt;

/**
 *
 * @author RSMXD001
 */
public class Filter {
    
    private Filter(){}
    
    public Filter(String route,String zone) {
        this.route=route;
        this.zone=zone;
    }
    
    private String route;
    private String zone;
    
    public boolean check(String PROF, int PROFDIST) {
        String conPROF = PROF.substring(0, 1); //izvadi prvi karakter iz PROF
        String conPROF2 = PROF.substring(0, 2); //izvadi prva dva karaktera iz PROF
        String conPROF3 = PROF.substring(0, 3); //izvadi prva tri karaktera iz PROF
        String conPROF4 = PROF.substring(0, 4); //izvadi prvih cetiri karaktera iz PROF
        String conPROF6="";
        if (PROF.length()>5)  conPROF6 = PROF.substring(0, 6); //izvadi prvih sest karaktera iz PROF
        String conPROF8="";
        if (PROF.length()>7)  conPROF8 =PROF.substring(0, 8); //izvadi prvih osam karaktera iz PROF*/
        switch (route){
                case "Eastern Leg":
                {
                    switch(zone){
                        case "0": return (((PROFDIST >= 132801) && (PROFDIST < 146700)) && (conPROF .equals( "3")));
                        case "1": return (((PROFDIST >= 147800) && (PROFDIST < 158800)) && (conPROF .equals( "4"))) || (((PROFDIST >= 158800) && (PROFDIST < 167700)) && (conPROF.equals("3")))
                                                    || (((PROFDIST >= 165700) && (PROFDIST < 175682)) && (conPROF.equals("4"))) 
                                                    || (((PROFDIST >= 0) && (PROFDIST < 4391)) && (conPROF.equals("3"))) || (((PROFDIST >= 167500) && (PROFDIST < 168000)) && (conPROF4.equals("H2EL")));
                        case "2": return (((PROFDIST >= 168100) && (PROFDIST < 183350)) && (conPROF .equals( "3")));
                        case "3": return (((PROFDIST >= 183750) && (PROFDIST < 192339)) && (conPROF .equals( "3")));                        
                        case "4": return (((PROFDIST >= 168742) && (PROFDIST < 193429)) && (conPROF4 .equals( "H2EL")));
                        case "5": return (((PROFDIST >= 194171) && (PROFDIST < 213629)) && (conPROF4 .equals( "H2EL")));                      
                        case "6": return (((PROFDIST >= 194171) && (PROFDIST < 213629)) && (conPROF4 .equals( "H2EL")));
                        case "7": return (((PROFDIST >= 214371) && (PROFDIST < 246288)) && (conPROF4 .equals( "H2EL")) || (((PROFDIST >= 244793) && (PROFDIST < 247160)) && (conPROF4.equals("H2SS"))) || (conPROF6.equals("H2L_TO")))  ;                       
                        case "8": return (((PROFDIST >= 247030) && (PROFDIST < 277716)) && (conPROF4 .equals( "H2EL")) || ((PROFDIST >= 247892) && (PROFDIST < 249184) && (conPROF4 .equals( "H2SS"))) ||                                
                                ((PROFDIST >= 249170) && (PROFDIST < 251871) && (conPROF4 .equals( "H2SY")))
                                                           
//                                ovo je kad se koristi sa sheffield loop
//                                ||
//                                
//                                ((PROFDIST >= 251871) && (PROFDIST < 254402) && (conPROF4 .equals( "H2SY"))) ||
//                                ((PROFDIST >= 227778) && (PROFDIST < 230378) && (conPROF4 .equals( "EREW"))) ||
//                                ((PROFDIST >= 230376) && (PROFDIST < 263812) && (conPROF4 .equals( "SHEF"))) ||
//                                ((PROFDIST >= 260653) && (PROFDIST < 269994) && (conPROF3 .equals( "TJC")))
//                                                          
                                );
                        case "9": return (((PROFDIST >= 278458) && (PROFDIST < 295264)) && (conPROF4 .equals( "H2EL")));                          
                        case "10":return (((PROFDIST >= 296006) && (PROFDIST < 320838)) && (conPROF4 .equals( "H2EL")) || (((PROFDIST >= 296756) && (PROFDIST < 302362)) && (conPROF4.equals("H2SS"))) 
                                                    || (((PROFDIST >= 319349) && (PROFDIST < 320525)) && (conPROF4.equals("H2LS"))));
                        case "11":return (((PROFDIST >= 321580) && (PROFDIST < 343527)) && (conPROF4 .equals( "H2EL")) || (((PROFDIST >= 321267) && (PROFDIST < 331799)) && (conPROF4.equals("H2LS"))));      
                        case "1_1":return ((PROFDIST >= 0) && (PROFDIST < 25700)) && (conPROF .equals( "0")||conPROF .equals( "1")||conPROF .equals( "2"));
                        case "1_2":return  ((PROFDIST >= 26100) && (PROFDIST < 49300)) && (conPROF .equals( "2"));     
                        
                        //       NOVO 27.12.2018
                        case "Sheffield": return (
                                ((PROFDIST >= 251871) && (PROFDIST < 254402) && (conPROF4 .equals( "H2SY"))) ||
                                ((PROFDIST >= 227779) && (PROFDIST < 230377) && (conPROF4 .equals( "EREW"))) || 
                                ((PROFDIST >= 230377) && (PROFDIST < 263810) && (conPROF4 .equals( "SHEF"))) ||
                                ((PROFDIST >= 260656) && (PROFDIST < 271651) && (conPROF3 .equals( "TJC")))  ||
                                (conPROF3 .equals( "SMJ")) ||
                                ((PROFDIST >= 296791) && (PROFDIST < 299866) && (conPROF4 .equals( "H2SS"))));
                         
                        /*       STARO
                        case "Sheffield": return ( ((PROFDIST >= 251871) && (PROFDIST < 254402) && (conPROF4 .equals( "H2SY"))) ||
                                ((PROFDIST >= 227778) && (PROFDIST < 230378) && (conPROF4 .equals( "EREW"))) ||
                                ((PROFDIST >= 230376) && (PROFDIST < 263812) && (conPROF4 .equals( "SHEF"))) ||
                                ((PROFDIST >= 260653) && (PROFDIST < 269994) && (conPROF3 .equals( "TJC"))));
                        */
                   }
                }
                
                case "Western Leg": {
                    switch(zone){
                        case "1": return (((PROFDIST >= 183920) && (PROFDIST < 193926) && (conPROF .equals("3"))) || ((PROFDIST >= 188250) && (PROFDIST < 198599) && (conPROF3.equals("OPB"))));
                        case "2": return ((PROFDIST >= 199341) && (PROFDIST < 225000) && (conPROF3 .equals("OPB")));
                        case "3": return (((PROFDIST >= 225742) && (PROFDIST < 245926) && (conPROF3 .equals("OPB"))) || (conPROF2.equals("CC")) || ((PROFDIST >= 245950) && (PROFDIST < 255629) && (conPROF4.equals("H2ML"))));
                        case "4": return (((PROFDIST >= 256371) && (PROFDIST < 279128) && (conPROF4 .equals("H2ML"))) || ((PROFDIST >= 276845) && (PROFDIST < 279014) && (conPROF4 .equals("H2MS"))) || conPROF4 .equals("H2WD"));                           
                        case "5": return ((PROFDIST >= 279756) && (PROFDIST < 306000) && (conPROF4 .equals("H2MS")));
                        case "6": return ((PROFDIST >= 279868) && (PROFDIST < 301574) && (conPROF4 .equals("H2ML")));                           
                    }
                }
                
                case "Custom":
                    switch(zone){
                        
                        //filter je isti kao i Western Leg
                        case "1": return (((PROFDIST >= 183920) && (PROFDIST < 193926) && (conPROF .equals("3"))) || ((PROFDIST >= 188250) && (PROFDIST < 198599) && (conPROF3.equals("OPB"))));
                        case "2": return ((PROFDIST >= 199341) && (PROFDIST < 225000) && (conPROF3 .equals("OPB")));
                        case "3": return (((PROFDIST >= 225742) && (PROFDIST < 245926) && (conPROF3 .equals("OPB"))) || (conPROF2.equals("CC")) || ((PROFDIST >= 245950) && (PROFDIST < 255629) && (conPROF4.equals("H2ML"))));  
                        case "4": return (((PROFDIST >= 256371) && (PROFDIST < 279128) && (conPROF4 .equals("H2ML"))) || ((PROFDIST >= 276845) && (PROFDIST < 279014) && (conPROF4 .equals("H2MS"))) || conPROF4 .equals("H2WD"));                           
                        case "5": return ((PROFDIST >= 279756) && (PROFDIST < 306000) && (conPROF4 .equals("H2MS")));
                        case "6": return ((PROFDIST >= 279868) && (PROFDIST < 301574) && (conPROF4 .equals("H2ML")));   
                    }
                    
                default: System.out.println("Invalid route. Check Filter.java file!!!");
        }
        return true;
    }
    
}
