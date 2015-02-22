package frauca

import grails.transaction.Transactional

@Transactional
class CategorizerService {

	def replicateMostManualSetted() {
		manualCategorizedConcept().each{concept->
			
			def cids=orderedManualSetted(concept)
			log.trace "categorizin "+concept+" cids "+cids
			Category category = cids[0][0]
			ucategoryzedMov(concept).each{mov->
				log.debug "categorizin "+mov.id+" with type "+category.name
				Categoritzation ct = new Categoritzation(type:SetterType.AUTOMATIC,category:category)
				mov.categoritzation = ct
				mov.save();
			}
		}
	}

	def orderedManualSetted(String concept){
		Categoritzation.executeQuery("""select cat.category,count(cat.category) as num
                                    from Categoritzation cat
                                     where cat.id in (select categoritzation from AccountMov where concept = '${concept}' and type in('MANUAL'))
                                     group by cat.category
                                     order by num desc
                                    """)
	}
	
	def orderedUncategorizedConcepts(){
		Categoritzation.executeQuery("""select concept,count(1) as num
                                    from AccountMov mov
                                     where mov.categoritzation is null
                                     group by concept
                                     order by num desc
                                    """)
	}

	def manualCategorizedConcept(){
		def q=AccountMov.withCriteria{
			projections {groupProperty("concept")}
			categoritzation{ eq "type",SetterType.MANUAL }
		}
	}
	
	def ucategoryzedMov(String concept){
        def query=AccountMov.where{
            eq ('concept',concept)
            isNull('categoritzation')
        }.list()
    }
	
	
	def manualCatUncategorized(String concept, Category category){
		ucategoryzedMov(concept).each {mov->
			Categoritzation ct = new Categoritzation(type:SetterType.MULTIPLE,category:category)
			mov.categoritzation = ct
			mov.save();
		}
	}
}
