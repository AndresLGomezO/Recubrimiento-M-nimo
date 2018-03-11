
package operaciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DatosXpath.NodosXPath;


public class Operaciones {

	private Operaciones() {
		
	}

	public static Set<Atributos> cierre(Set<Atributos> attrs, Set<FuncDep> fds) {
		Set<Atributos> result = new HashSet<>(attrs);

		boolean found = true;
		while (found) {
			found = false;
			for (FuncDep fd : fds) {
				if (result.containsAll(fd.left) && !result.containsAll(fd.right)) {
					result.addAll(fd.right);
					found = true;
				}
			}
		}

		return result;
	}
	

	
	public static Set<FuncDep> getL1(Set<FuncDep> fds) {
		Set<FuncDep> result =   new HashSet<>();			
		for (FuncDep fd : fds) {
			if(fd.left.size()>1) {

				List<Atributos> leftList = new ArrayList<>();
				HashSet<Atributos> extranos = new HashSet<>();
				boolean noExtrano = false;
				for (Atributos a : fd.left) {

					// Validación atributos extraños

					List<Atributos> rightList = new ArrayList<>(fd.getRight());
					Atributos rightAttribute = rightList.get(0);

					noExtrano = false;
					List<Atributos> newLeft = new ArrayList<>();

					for (Atributos b : fd.left) {

						if (!b.equals(a) && !extranos.contains(b)) {

							newLeft.add(b);

						}

					}
					Set<Atributos> newLeftSideSet = new HashSet<Atributos>(newLeft);
					Set<Atributos> cierreSet = cierre(newLeftSideSet, fds);

					// Si el atributo a la der hace parte del cierre

					if (cierreSet == null || !cierreSet.contains(rightAttribute)) {

						// No extraño
						leftList.add(a);
						noExtrano = true;

					}

					if (!noExtrano) {
							//Atributo extraño
						extranos.add(a);
					}

				}

				if (leftList.size() < fd.left.size()) {

					if (!leftList.isEmpty()) {
						Set<Atributos> minLeftAttributes = new HashSet<Atributos>(leftList);
						FuncDep newDF = new FuncDep(minLeftAttributes,fd.right);

						// Evaluar nueva dependencia 

						Set<Atributos> verifyClosure = cierre(newDF.left, fds);
						List<Atributos> reducedRigth = new ArrayList<>(newDF.right);
						
						if (verifyClosure.contains(reducedRigth.get(0))) {
							if (!result.contains(newDF)) {
								result.add(newDF);
							}

						}

					}

				} else {
					if (!result.contains(fd)) {
						result.add(fd);
					}
				}

			}else {
				if (!result.contains(fd)) {
					result.add(fd);
				}
			}
		}
		return result;
	}
	
	public static Set<FuncDep> l0(Set<FuncDep> fds) {
		Set<FuncDep> toRemove = new HashSet<>();
		Set<FuncDep> toAdd = new HashSet<>();

		for (FuncDep fd : fds) {
			if (fd.right.size() > 1) {
				for (Atributos a : fd.right) {
					toAdd.add(new FuncDep.Builder().left(fd.left).right(a).build());
				}
				toRemove.add(fd);
			} 
		}
		fds.addAll(toAdd);
		fds.removeAll(toRemove);
		
		return fds;

	}
	public static Set<FuncDep> getl2(Set<FuncDep> fds) {
		
		Set<FuncDep> FINAL = new HashSet<>();
		FINAL.addAll(fds);
		for (FuncDep fd : fds) {
			Set<FuncDep> toRemove = new HashSet<>();
			toRemove.addAll(FINAL);
			toRemove.remove(fd);

			//System.out.println("Cierre " + Operaciones.cierre(fd.getLeft(), toRemove));
				if (Operaciones.cierre(fd.getLeft(), toRemove).containsAll(fd.getRight()) ){
					FINAL.remove(fd);
				}
			}
		return FINAL;
		}
		

	
	
	
	public static Set<Set<Atributos>> TodasLasLlaves(){
		System.out.println("TODAS LAS LLAVES  ");
		Set<Set<Atributos>> all = Operaciones.reduction(NodosXPath.attrs);
		Set<Set<Atributos>> keys = new HashSet<Set<Atributos>>();
	   Iterator<Set<Atributos>> It2 = all.iterator();
		while (It2.hasNext()) {
	   	   Set<Atributos> n = new HashSet<>();
		   n.addAll(It2.next());
		   if(Operaciones.cierre(n, NodosXPath.fds).equals(NodosXPath.attrs))
		      {
			   System.out.println("Llave agregada " + n);
		        keys.add(n);
		      } 
	   }
	    //The whole relation is always a superkey!!!
	    keys.add(NodosXPath.attrs);
	    return keys;
	  }
		

		public static Set<Set<Atributos>> reduction(Set<Atributos> Atribut){
			    //returns a new set
			 Set<Set<Atributos>> r = new HashSet<Set<Atributos>>();
			 Set<Atributos> New = new HashSet<>();
			 New.addAll(Atribut);
			Iterator<Atributos> It1 = New.iterator();
			while(It1.hasNext()) {   
				
			    	Set<Atributos> c = new HashSet<>();
			    	c.addAll(New);
			    	
			    	
			    	c.remove(It1.next());
			    	
			    	if(!c.isEmpty()){
			    			r.add(c);
			    			Set<Set<Atributos>> re = new HashSet<Set<Atributos>>();
			    			re.addAll(Operaciones.reduction(c));
			    			if(!r.containsAll(re)) {
			    				r.addAll(re);
			    				}
			    			}
			    		}
			    return r;
			    }
		

		 
		public static Set<Set<Atributos>> LlavesCandidatas(){
			Set<Set<Atributos>> sk = new HashSet<Set<Atributos>>();
			sk.addAll(Operaciones.TodasLasLlaves());
			Set<Set<Atributos>> cand = new HashSet<Set<Atributos>>();
		    Iterator<Set<Atributos>> i = sk.iterator();
		    boolean flag = false;
		    while(i.hasNext()){
		    	Set<Atributos> a = i.next();
		    	
		    
		    Set<Set<Atributos>> c = Operaciones.reduction(a);
		     
		   	    
		  
		      Iterator<Set<Atributos>> j = c.iterator();
		     
		      flag = true;
		      while(j.hasNext()){
		    	
		    	 
		    	  
		    	Set<Atributos> d = j.next();
		    	
		    	      
		    
		        if(Operaciones.cierre(d, NodosXPath.fds).equals(NodosXPath.attrs)) {
		        	
		        	System.out.println("Llave candidata " + a);
		          flag = false;
		        
		    	  
		      
		      }
		      }
		      if(flag == true) {
		        cand.add(a);

		    }
		    
		
		    }

		    return cand;
		    
		
		}


	
}
