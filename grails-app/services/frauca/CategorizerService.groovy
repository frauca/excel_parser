package frauca

import grails.transaction.Transactional

@Transactional
class CategorizerService {

	def replicateMostManualSetted() {
		log.debug "categorizing the ones that has been maunual or multiple setted"
		manualCategorizedConcept().each{concept->
			
			Category category = autoCategoryByConcept(concept)
			Category subcategory = autoSubCategoryByConcept(concept)
			ucategoryzedMov(concept).each{mov->
				log.debug "categorizin the ones uncategorized ${concept} "+mov.id+" with type "+category.name
				Categoritzation ct = new Categoritzation(type:SetterType.AUTOMATIC,category:category,subcat:subcategory)
				mov.categoritzation = ct
				mov.save();
			}
			if(subcategory){
				ucategoryzedNotSubcat(concept).each{mov->
					log.debug "categorizin the ones not subcategorized ${concept} "+mov.id+" with type "+subcategory.name
					mov.categoritzation.subcat=subcategory
					mov.save();
				}
			}
		}
	}

	Category autoCategoryByConcept(String concept){
		def cids=orderedManualSetted(concept)
		log.trace "categorizin "+concept+" cids "+cids
		Category category = cids[0][0]
		return category
	}
	
	Category autoSubCategoryByConcept(String concept){
		def cids=orderedSubCatManualSetted(concept)
		log.trace "categorizin sub "+concept+" cids "+cids
		if(cids){
			Category category = cids[0][0]
		}else{
			null
		}
	}
	
	def orderedManualSetted(String concept){
		 Categoritzation.executeQuery("""select cat.category,
                                         count(cat.category) as total,
                                         (select count(1) from AccountMov mov where mov.concept = '${concept}' and mov.categoritzation.type='MANUAL') as num,
                                         (select count(1) from AccountMov mov where mov.concept = '${concept}' and mov.categoritzation.type='MULTIPLE') as mum
                                    from Categoritzation cat
                                     where cat.id in (select categoritzation from AccountMov where concept = '${concept}')
                                     and type in ('MANUAL','MULTIPLE')
                                     group by cat.category
                                     order by num desc
                                    """)
	}
	
	def orderedSubCatManualSetted(String concept){
		Categoritzation.executeQuery("""select cat.subcat,
                                         count(1) as total,
                                         (select count(1) from AccountMov mov where mov.concept = '${concept}' and mov.categoritzation.type='MANUAL') as num,
                                         (select count(1) from AccountMov mov where mov.concept = '${concept}' and mov.categoritzation.type='MULTIPLE') as mum
                                    from Categoritzation cat
                                     where cat.id in (select categoritzation from AccountMov where concept = '${concept}')
                                     and type in ('MANUAL','MULTIPLE')
                                     group by cat.subcat
                                     order by num desc
                                    """)
   }
	def orderedUncategorizedConcepts(){
		Categoritzation.executeQuery("""select mov.concept
										,count(1) as total
                            			,(select count(1) from AccountMov as m3 left outer join m3.categoritzation as cat where m3.concept=mov.concept and cat is null) as nn
                                    from AccountMov mov
                                     group by mov.concept
                                     order by nn desc
                                    """)
	}
	
	def categorizedByConcept(concept){
		log.trace "%${concept}%"
		AccountMov.executeQuery("""select mov.concept as concept
                            ,count(1) as total
                            ,(select count(1) from AccountMov as m3 left outer join m3.categoritzation as cat where m3.concept=mov.concept and cat is null) as nn
                                    from AccountMov as mov
                                    where mov.concept like ?
                                    group by mov.concept
									order by nn desc
                                    ""","%${concept}%")
	}

	def manualCategorizedConcept(){
		AccountMov.executeQuery("""select mov.concept
                                    from AccountMov mov
                                     where mov.categoritzation.type in ('MANUAL','MULTIPLE')
                                     group by mov.concept
                                    """)
	}
	
	def ucategoryzedNotSubcat(String concept){
        AccountMov.executeQuery("""from AccountMov mov
                                     where concept = ?  and 
										mov.categoritzation is not null and
	                                    mov.categoritzation.subcat is null and 
										0 < (select count(1) from Category where mov.categoritzation.category.id = father.id)
                                    """,concept)
    }
	
	def ucategoryzedMov(String concept){
		AccountMov.executeQuery("""from AccountMov mov
                                     where concept = ?  and 
										mov.categoritzation is null 
										)
                                    """,concept)
	}
	
	
	def manualCatUncategorized(String concept, Category category){
		ucategoryzedMov(concept).each {mov->
			Categoritzation ct = new Categoritzation(type:SetterType.MULTIPLE,category:category)
			mov.categoritzation = ct
			mov.save();
		}
	}
}
