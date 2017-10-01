package items;



import unidades.Unidad;

public class Capa extends UnidadDecorator  {

	public Capa(Unidad unidad) {
		super(unidad);
	}
	
	
	
	@Override
	public double getDa�o() {
		if(this.unidad.getDa�o() == 4.5 || this.unidad.getDa�o() == 9 || this.unidad.getDa�o() == 22.5 || this.unidad.getDa�o() == 45 ||  
			this.unidad.getDa�o() == 11.7 || this.unidad.getDa�o() == 7.2 || this.unidad.getDa�o() == 25.2 || this.unidad.getDa�o() == 47.7 ||
					this.unidad.getDa�o() == 7.5 || this.unidad.getDa�o() == 12 || this.unidad.getDa�o() == 25.5 || this.unidad.getDa�o() == 48)
			return this.unidad.getDa�o();
		else
			return this.unidad.getDa�o() * 0.9;

	}
	


	@Override
	public double getEnergia() {
		
		if(this.unidad.getEnergia()<=50)
			return this.unidad.getEnergia()*2; 
		
		else {
			
				return this.unidad.getEnergia() + (100 - this.unidad.getEnergia());
			}
	}	
}
