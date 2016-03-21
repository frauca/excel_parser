package frauca.accountsmovs

import frauca.AccountMov
import grails.transaction.Transactional

/**
 * @author rofc
 * This class will help when some problem arrise. It keep the Movs With the same date. So if some problem arrise it will try to reorder them to try tom make te totals do match the totalMaount Calculation
 */
@Transactional
class TotalsAmountService {

   
    
    /**
     * On the pos it must be an error. It will try to find all the movs with the same date as pos.
     * It will try to order all this elements to try they macthc the totals. And then i will reorder the list
     * If some order is found it will reorder el order values
     * @param all
     * @param pos
     * @return
     */
    boolean couldSolveIt(AccountMov[] all,int pos) {
		TotalAmountCommand totalC=new TotalAmountCommand(all,pos)
		for(int i=pos;i<totalC.toPos;i++){
			if(!findThisPos(totalC,i)){
				log.debug("As I could not find any valid one, i could not resolve it")
				return false
			}
		}
		return true
    }
	
	/**
	 * Recalc the orderOfDoc of the fileSource to orderThem
	 * @param all
	 * @param pos
	 * @return
	 */
	boolean resetOrdersOfDoc(AccountMov[] all,int pos){
		TotalAmountCommand totalC=new TotalAmountCommand(all,pos)
	}
	
	/**
	 * Look on the next values to see if some one meets the correct values
	 * @param totalC
	 * @param i
	 * @return
	 */
	boolean findThisPos(TotalAmountCommand totalC,int p){
		AccountMov prev=totalC.all[p-1]
		for(int i=p;i<totalC.toPos;i++){
			AccountMov curr=totalC.all[i]
			if(prev.totalAmountRaw+curr.amount==curr.totalAmountRaw){
				changePosToPos(totalC,p,i)
				return true
			}
		}
		return false
	}
	
	
	/**
	 * Change one thate for another
	 * @param totalC
	 * @param from
	 * @param to
	 * @return
	 */
	def changePosToPos(TotalAmountCommand totalC,int from,int to){
		AccountMov prev=totalC.all[from-1]
		AccountMov tmp=totalC.all[to]
		totalC.all[to]=totalC.all[from]
		totalC.all[from]=tmp
		totalC.all[from].original.orderOfDoc=prev.original.orderOfDoc+1
		
	}
	
	class TotalAmountCommand{
		public int pos
		public int fromPos
		public int toPos
		public AccountMov[] withSameDateAsErrors
		public AccountMov[] all
		public AccountMov erroOne
		public AccountMov beforeErro
		
		public TotalAmountCommand(AccountMov[] all,int pos){
			this.all=all
			this.pos=pos
			erroOne=all[pos]
			beforeErro=all[pos-1]
			withSameDateAsErrors()
		}
		/**
		 * @param all
		 * @param pos
		 * @return the elements with the same date
		 */
		public AccountMov[] withSameDateAsErrors() {
			def res=[]
			for(int i=pos;i>=1;i--){
				AccountMov current=all[i];
				if(all[i-1].operationDate==current.operationDate){
					res=[all[i-1]]+res
				}else{
					fromPos=i;
					break
				}
			}
			res+=all[pos]
			for(int i=pos;i<all.size();i++){
				AccountMov current=all[i];
				if(all[i+1].operationDate==current.operationDate){
					res+=all[i+1]
				}else{
					toPos=i;
					break
				}
			}
		}
	}
}
