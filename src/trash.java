import java.util.List;

import co.edu.uptc.calls.repository.Call;

public class trash {

}


this.mapCostoBaseKm = new HashMap<String, Double>();
incrementoPorTipoViaje = new HashMap<TipoViajeEnum, Float>();
incrementoPorTipoViaje.put(TipoViajeEnum.URBANO, 0f);
incrementoPorTipoViaje.put(TipoViajeEnum.INTERMUNICIPAL, 0.05f);
incrementoPorTipoViaje.put(TipoViajeEnum.NACIONAL, 0.1f);
incrementoPorTipoViaje.put(TipoViajeEnum.INTERNACIONAL, 0.2f);





public Call newCall(Call call);
public Call findKey(String key);
public List<Call> findKey;
public Call updateCall(Call newCall);
public void deleteKey(String key);
public boolean existingKey(String key);