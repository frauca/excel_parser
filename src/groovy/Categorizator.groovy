import frauca.*;
import grails.gorm.*;

def notNullCate(){
    def query=AccountMov.where{
        isNotNull('categoritzation')
    }
}
//notNullCate().list()
def catPosibleToCat(){
 def query=AccountMov.withCriteria {
    eq "id",  new DetachedCriteria(AccountMov).build{ 
             projections{id}
            isNotNull('categoritzation')  
           
       }
 }
}

def movWithManualCat(){
   def query=AccountMov.where{
        categoritzation.type=='MANUAL';
    }  
}
 //movWithManualCat().list();
def catPosibleToCat2(){
     AccountMov.executeQuery("""select concept,count(mov) as tot from AccountMov mov
                                 where categoritzation is null 
                                     and concept in (select concept from AccountMov where categoritzation is not null) 
                                 group by concept
                                 order by tot desc """);

}
//catPosibleToCat2()

def manualCategorizedConcept(){
        def q=AccountMov.withCriteria{
            projections {groupProperty("concept")}
            categoritzation{
                eq "type",SetterType.MANUAL
            }
       }
   }

println manualCategorizedConcept()
def orderedManualSetted(String concept){
    Categoritzation.executeQuery("""select cat.category,count(cat.category) as num
                                    from Categoritzation cat
                                     where cat.id in (select categoritzation from AccountMov where concept = '${concept}')
                                     group by cat.category
                                     order by num
                                    """)
   }
manualCategorizedConcept().each(){   
   println  orderedManualSetted(it)[0][0]
    }
    
    
def ucategoryzedMov(String concept){
        def query=AccountMov.where{
            eq ('concept',concept)
            isNull('categoritzation')
        }
    }
    
     ucategoryzedMov("INVICAT   BRA.VILASSAR N.").list()