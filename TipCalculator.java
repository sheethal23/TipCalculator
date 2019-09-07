/************************************************************
*                                                          *
*                                                          *
*  Class Name:  TipCalculator.                             *
*                                                          *
*  Programmer:  SHEETHAL YELLISETTY                        *
*                                                          *
*                                                          *
*  Purpose:     TipCalculator to calculate the tip         *
*               percentage and share of individual.        * 
*               .                                          *
*                                                          *
************************************************************/
public class TipCalculator                     //Tip calculator class
  {
	private double billamt = 0;                //instance variables
	private int tippercent = 20;
	private int partysize = 1;
	
	public TipCalculator()                    //default constructor
	{
		
	}
	/************************************************************
	 *                                                          *
	 *  Method Name:  setBillamt().                             *
	 *                                                          *
	 *  Purpose:  Method that sets the value of the billamount. *
	 *                                                          *
	 *                                                          *
	 ************************************************************/
	public void setBillamt(double billamt) {    //Billamt setmethod
		this.billamt = billamt;
	}

	/************************************************************
	 *                                                          *
	 *  Method Name:  setTippercent().                          *
	 *                                                          *
	 *  Purpose:  Method that sets the value of the Tippercent. *
	 *                                                          *
	 *                                                          *
	 ************************************************************/
	public void setTippercent(int tippercent) {
		this.tippercent = tippercent;
	}
	
	/************************************************************
	 *                                                          *
	 *  Method Name:  setPartysize().                           *
	 *                                                          *
	 *  Purpose:  Method that sets the size of the party.       *
	 *                                                          *
	 *                                                          *
	 ************************************************************/
	public void setPartysize(int partysize) {
		this.partysize = partysize;
	}
	
	/************************************************************
	 *                                                          *
	 *  Method Name:  getTotalBill().                           *
	 *                                                          *
	 *  Purpose:  Method that calculates the TotalBill.         *
	 *                                                          *
	 *                                                          *
	 ************************************************************/
	public double getTotalBill(double enteredbill)    //method to calculate totalbill
	{
		billamt = enteredbill + billamt;
		double c = tippercent*0.01*billamt;
		return billamt + c ;		
	}
	
	/************************************************************
	 *                                                          *
	 *  Method Name:  getIndividualShare().                     *
	 *                                                          *
	 *  Purpose:  Method that calculates the Individual share.  *
	 *                                                          *
	 *                                                          *
	 ************************************************************/
	public double getIndividualShare(double totbill,int enteredsize)  //method to calculate Individualshare
	{
		int partysize1 = enteredsize * partysize;
		return totbill/partysize1;	
	}

}
