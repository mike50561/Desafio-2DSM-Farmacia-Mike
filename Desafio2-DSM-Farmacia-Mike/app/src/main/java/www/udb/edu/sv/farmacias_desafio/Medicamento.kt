package www.udb.edu.sv.farmacias_desafio



import android.os.Parcel
import android.os.Parcelable

data class Medicamento(val nombre: String, val precio: Double) : Parcelable {
    // Constructor que lee desde el Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readDouble()
    )

    // Este método escribe los datos de la clase en el Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeDouble(precio)
    }

    override fun describeContents(): Int {
        return 0
    }

    // Esto es requerido para crear la instancia desde el Parcel
    companion object CREATOR : Parcelable.Creator<Medicamento> {
        override fun createFromParcel(parcel: Parcel): Medicamento {
            return Medicamento(parcel)
        }

        override fun newArray(size: Int): Array<Medicamento?> {
            return arrayOfNulls(size)
        }
    }
}
