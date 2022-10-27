package com.ordersystem.pizzaorder.service.impl;

import com.ordersystem.pizzaorder.dto.*;
import com.ordersystem.pizzaorder.dto.builder.PizzaBuilder.BuilderPizzaDTO;
import com.ordersystem.pizzaorder.dto.wrappercollectionDTO.CollectionSetWrapper;
import com.ordersystem.pizzaorder.entity.*;
import com.ordersystem.pizzaorder.exception.generic.InvalidParameter;
import com.ordersystem.pizzaorder.exception.generic.NotFoundEntity;
import com.ordersystem.pizzaorder.exception.generic.NullException;
import com.ordersystem.pizzaorder.repository.*;
import com.ordersystem.pizzaorder.service.PizzaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final ModelMapper modelMapper;
    private final DimensiunePizzaRepository dimensiunePizzaRepository;
    private final IngredientRepository ingredientRepository;
    private final SosuriBlatPizzaRepository sosuriBlatPizzaRepository;
    private final BlatPizzaRepository tipuriblatPizzaRepository;
    private final TopingPizzaRepository topingPizzaRepository;

    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository, ModelMapper modelMapper, DimensiunePizzaRepository dimensiunePizzaRepository, IngredientRepository ingredientRepository, SosuriBlatPizzaRepository sosuriBlatPizzaRepository, BlatPizzaRepository tipuriblatPizzaRepository, TopingPizzaRepository topingPizzaRepository) {
        this.pizzaRepository = pizzaRepository;
        this.modelMapper = modelMapper;

        this.dimensiunePizzaRepository = dimensiunePizzaRepository;
        this.ingredientRepository = ingredientRepository;
        this.sosuriBlatPizzaRepository = sosuriBlatPizzaRepository;
        this.tipuriblatPizzaRepository = tipuriblatPizzaRepository;
        this.topingPizzaRepository = topingPizzaRepository;
    }

    /**
     * Extrage si returneaza o colectie cu toate produsele de tip pizza. (Doar informatiile de baza)
     *
     * @return Set<PizzaDTO> - returneaza o colectie de elemente de tip 'PizzaDTO'
     */
    @Override
    public Set<PizzaDTO> getAllPizzas() {
        return pizzaRepository.findAll().stream().map(
                        i -> new BuilderPizzaDTO()
                                .setIdPizza(i.getIdPizza())
                                .setDenumirePizza(i.getNumePizza())
                                .setPretPizza(i.getPretPizza())
                                .setUrlPizza(i.getUrlPizza())
                                .build())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * Functie care extrage doar informatiile de baza a unui obiect pizza;
     *
     * @param id - parametrul de intrare trimis de client cu scopul de a indentifica pizza dorita
     * @return PizzaDTO - obiect destinat transferului de informatii catre client
     */
    @Override
    public PizzaDTO getPizzaById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("[ID : " + id + "] Id should be in : [0,infinity]");
        }
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new NotFoundEntity("Current id invalid"));
        return new BuilderPizzaDTO()
                .setIdPizza(pizza.getIdPizza())
                .setDenumirePizza(pizza.getNumePizza())
                .setPretPizza(pizza.getPretPizza())
                .build();
    }

    /**
     * Functia returneaza din tabelul 'pizza' un obiect DTO cu datele complete referitoare la pizza ceruta
     * (+ relatiile acestora )
     *
     * @param id - parametrul de intrare trimis de client avand rolul de a indentifica pizza dorita
     * @return PizzaDTO - obiect destinat transferului de informatii catre client
     */
    @Override
    public PizzaDTO getPizzaFullPizza(Long id) {
        if (id < 0) {
            throw new InvalidParameter("[ID : " + id + "] Id should be in : [0,infinity]");
        }
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new NotFoundEntity("Current id invalid"));

        return new BuilderPizzaDTO()
                .setIdPizza(pizza.getIdPizza())
                .setDenumirePizza(pizza.getNumePizza())
                .setPretPizza(pizza.getPretPizza())
                .setUrlPizza(pizza.getUrlPizza())
                .setDimensiuneDTOS(pizza.getDimensiunePizzas().stream().map(i -> modelMapper.map(i, DimensiuneDTO.class)).collect(Collectors.toCollection(LinkedHashSet::new)))
                .setSosBlatDTOS(pizza.getSosBlatPizzas().stream().map(i -> modelMapper.map(i, SosBlatDTO.class)).collect(Collectors.toCollection(LinkedHashSet::new)))
                .setTipBlatDTOS(pizza.getTipuriBlatPizzas().stream().map(i -> modelMapper.map(i, TipBlatDTO.class)).collect(Collectors.toCollection(LinkedHashSet::new)))
                .setTopingDTOS(pizza.getTopinguriPizzas().stream().map(i -> modelMapper.map(i, TopingDTO.class)).collect(Collectors.toCollection(LinkedHashSet::new)))
                .setIngredientDTOS(pizza.getIngredientePizzas().stream().map(i -> modelMapper.map(i, IngredientDTO.class)).collect(Collectors.toCollection(LinkedHashSet::new)))
                .build();
    }


    /**
     * Functia insereaza in tabela 'pizzas' un nou tip de Pita
     * Datele de legatura se presupun a fi existente in tabela copil pentru a obtine legaturile dorite
     * in caz contrar aceasta inserare nu va avea loc si va arunca exceptii
     *
     * @param pizzaDTO - parametrul de intrare trimis de aplicatia client care mapeaza informatiile
     *                 necesare pentru adaugare
     */

    @Override
    public void savePizza(PizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza(null, pizzaDTO.getDenumirePizza(), pizzaDTO.getPretPizza(),pizzaDTO.getUrlPizza());

        // Adaugare dimensiunea unei pizze

        if (pizzaDTO.getDimensiuneDTOS() != null && !pizzaDTO.getDimensiuneDTOS().isEmpty()) {

            Long[] arrayIndex = pizzaDTO.getDimensiuneDTOS().stream()
                    .map(DimensiuneDTO::getIdDimensiune)
                    .toArray(Long[]::new);

            throwIfAnyNumberLessThanZero(arrayIndex);

            Set<DimensiunePizza> dimensiunePizzaSet = dimensiunePizzaRepository.getAllDimensiuniByParamList(arrayIndex);

            if (dimensiunePizzaSet.size() != arrayIndex.length) {
                throw new NotFoundEntity("Indexul introdus nu corespunde cu nici o inregistrare");
            }

            pizza.setDimensiunePizzas(dimensiunePizzaSet);

            dimensiunePizzaSet.forEach(i -> i.getPizzaSet().add(pizza));
        }

        //Adaugarea unui ingredient pizzei;
        if (pizzaDTO.getIngredientDTOS() != null && !pizzaDTO.getIngredientDTOS().isEmpty()) {

            Long[] arrayIndex = pizzaDTO.getIngredientDTOS().stream()
                    .map(IngredientDTO::getIdIngredient)
                    .toArray(Long[]::new);

            throwIfAnyNumberLessThanZero(arrayIndex);

            Set<IngredientePizza> ingredientePizzas = ingredientRepository.getAllIngredienteByParamList(arrayIndex);

            if (ingredientePizzas.size() != arrayIndex.length) {
                throw new NotFoundEntity("Invalid Index of colleciton ingrediente");
            }

            pizza.setIngredientePizzas(ingredientePizzas);

            ingredientePizzas.forEach(i -> i.getPizzas().add(pizza));
        }

        //Sos Blaturi
        if (pizzaDTO.getSosBlatDTOS() != null && !pizzaDTO.getSosBlatDTOS().isEmpty()) {

            Long[] arrayIndex = pizzaDTO.getSosBlatDTOS().stream()
                    .map(SosBlatDTO::getIdSos)
                    .toArray(Long[]::new);

            throwIfAnyNumberLessThanZero(arrayIndex);

            Set<SosBlatPizza> sosBlatPizzas = sosuriBlatPizzaRepository.getAllSosBlaturiByParamList(arrayIndex);

            if (sosBlatPizzas.size() != arrayIndex.length) {
                throw new NotFoundEntity("Invalid Index of colleciton sosBlaturi:");
            }

            pizza.setSosBlatPizzas(sosBlatPizzas);

            sosBlatPizzas.forEach(i -> i.getPizzas().add(pizza));
        }
        //Tipuri blaturi

        if (pizzaDTO.getTipBlatDTOS() != null && !pizzaDTO.getTipBlatDTOS().isEmpty()) {

            Long[] arrayIndex = pizzaDTO.getTipBlatDTOS().stream()
                    .map(TipBlatDTO::getIdTipBlat)
                    .toArray(Long[]::new);

            throwIfAnyNumberLessThanZero(arrayIndex);

            Set<TipuriBlatPizza> tipuriBlatPizzas = tipuriblatPizzaRepository.getAllTipuriBlatByParamList(arrayIndex);

            if (tipuriBlatPizzas.size() != arrayIndex.length) {
                throw new NotFoundEntity("Invalid Index of colleciton of TIpuriBlaturi");
            }

            pizza.setTipuriBlatPizzas(tipuriBlatPizzas);
            tipuriBlatPizzas.forEach(i -> i.getPizzas().add(pizza));
        }
        //Topinguri blaturi
        if (pizzaDTO.getTopingDTOS() != null && !pizzaDTO.getTopingDTOS().isEmpty()) {

            Long[] arrayIndex = pizzaDTO.getTopingDTOS().stream()
                    .map(TopingDTO::getIdToping)
                    .toArray(Long[]::new);

            throwIfAnyNumberLessThanZero(arrayIndex);

            Set<TopinguriPizza> topinguriPizzas = topingPizzaRepository.getAllTopingsByParamList(arrayIndex);

            if (topinguriPizzas.size() != arrayIndex.length) {
                throw new NotFoundEntity("Invalid Index of colleciton of topinguri");
            }

            pizza.setTopinguriPizzas(topinguriPizzas);
            topinguriPizzas.forEach(i -> i.getPizzas().add(pizza));
        }
        pizzaRepository.save(pizza);
    }

    /**
     * Functie care sterge din baza de date o pizza (sterge si relatiile acestora)
     *
     * @param id - parametru furnizat de client pentru stergerea produsului indentificat de acest id
     */
    @Override
    public void deletePizzaById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntity("Id-ul specificat nu se aflta in baza de date. Current id:[" + id + "]"));

        //Dimensiune
        if (pizza.getDimensiunePizzas() != null && !pizza.getDimensiunePizzas().isEmpty()) {
            pizza.getDimensiunePizzas().forEach(i -> i.getPizzaSet().remove(pizza));

            pizza.setDimensiunePizzas(null);
        }

        //Sos blat
        if (pizza.getSosBlatPizzas() != null && !pizza.getSosBlatPizzas().isEmpty()) {
            pizza.getSosBlatPizzas().forEach(i -> i.getPizzas().remove(pizza));

            pizza.setSosBlatPizzas(null);
        }

        //Ingrediente blat
        if (pizza.getIngredientePizzas() != null && !pizza.getIngredientePizzas().isEmpty()) {
            pizza.getIngredientePizzas().forEach(i -> i.getPizzas().remove(pizza));

            pizza.setIngredientePizzas(null);
        }

        //Tipuri blat
        if (pizza.getTipuriBlatPizzas() != null && !pizza.getTipuriBlatPizzas().isEmpty()) {
            pizza.getTipuriBlatPizzas().forEach(i -> i.getPizzas().remove(pizza));

            pizza.setTipuriBlatPizzas(null);
        }

        //Topinguri
        if (pizza.getTopinguriPizzas() != null && !pizza.getTopinguriPizzas().isEmpty()) {
            pizza.getTopinguriPizzas().forEach(i -> i.getPizzas().remove(pizza));

            pizza.setTopinguriPizzas(null);
        }

        pizzaRepository.delete(pizza);
    }

    //TO DO: Adaugare / Stergere / Modificare (doar pizza) una dintre componente;
    @Override
    public void adaugaToppinguriByPizzaId(Long id, CollectionSetWrapper<TopingDTO> topingDTOSet) {
        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        if (topingDTOSet.getCollection() == null) {
            throw new NullException("Collectia nu trebuie sa fie null");
        }

        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundEntity("Id-ul specificat nu se aflta in baza de date. Current id:[" + id + "]"));

        if (topingDTOSet == null && topingDTOSet.getCollection().isEmpty()) {
            throw new NullException("Lista trebuie sa contina elemente");
        } else {

            Long[] arrayIndex = topingDTOSet.getCollection().stream()
                    .map(TopingDTO::getIdToping)
                    .toArray(Long[]::new);

            throwIfAnyNumberLessThanZero(arrayIndex);

            Set<TopinguriPizza> topinguriPizzas = topingPizzaRepository.getAllTopingsByParamList(arrayIndex);

            if (topinguriPizzas.size() != topingDTOSet.getCollection().size()) {

                List<Long> indexActual = topinguriPizzas.stream()
                        .map(TopinguriPizza::getIdToping)
                        .collect(Collectors.toCollection(ArrayList::new));

                List<Long> invalidIndex = Arrays.stream(arrayIndex).collect(Collectors.toCollection(ArrayList::new));
                invalidIndex.removeAll(indexActual);

                throw new NotFoundEntity("Indexurile: " + invalidIndex + " nu au fost gasite in baza de date");
            }

            topinguriPizzas.forEach(i -> i.getPizzas().add(pizza));
            pizza.getTopinguriPizzas().addAll(topinguriPizzas);
        }

        pizzaRepository.save(pizza);

    }

    //Better performance with @Query -> later ! Reminder
    @Override
    public void adaugareIngredienteByPizzaId(Long id, CollectionSetWrapper<IngredientDTO> ingredienteDTOSet) {
        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        if (ingredienteDTOSet.getCollection() == null) {
            throw new NullException("Collectia nu trebuie sa fie null");
        }

        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundEntity("Id-ul specificat nu se aflta in baza de date. Current id:[" + id + "]"));

        if (ingredienteDTOSet == null && ingredienteDTOSet.getCollection().isEmpty()) {
            throw new NullException("Lista trebuie sa contina elemente");
        } else {

            Long[] arrayIndex = ingredienteDTOSet.getCollection().stream()
                    .map(IngredientDTO::getIdIngredient)
                    .toArray(Long[]::new);

            throwIfAnyNumberLessThanZero(arrayIndex);

            Set<IngredientePizza> ingredientePizzas = ingredientRepository.getAllIngredienteByParamList(arrayIndex);

            if (ingredientePizzas.size() != ingredienteDTOSet.getCollection().size()) {

                List<Long> indexActual = ingredientePizzas.stream()
                        .map(IngredientePizza::getIdIngredient)
                        .collect(Collectors.toCollection(ArrayList::new));

                List<Long> invalidIndex = Arrays.stream(arrayIndex).collect(Collectors.toCollection(ArrayList::new));
                invalidIndex.removeAll(indexActual);

                throw new NotFoundEntity("Indexurile: " + invalidIndex + " nu au fost gasite in baza de date");
            }

            ingredientePizzas.forEach(i -> i.getPizzas().add(pizza));
            pizza.getIngredientePizzas().addAll(ingredientePizzas);
        }

        pizzaRepository.save(pizza);

    }

    @Override
    public void adaugareDimensinueByPizzaId(Long id, CollectionSetWrapper<DimensiuneDTO> dimensiuneDTOSet) {
        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        if (dimensiuneDTOSet.getCollection() == null) {
            throw new NullException("Collectia nu trebuie sa fie null");
        }


        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundEntity("Id-ul specificat nu se aflta in baza de date. Current id:[" + id + "]"));

        if (dimensiuneDTOSet == null && dimensiuneDTOSet.getCollection().isEmpty()) {
            throw new NullException("Lista trebuie sa contina elemente");
        } else {

            Long[] arrayIndex = dimensiuneDTOSet.getCollection().stream()
                    .map(DimensiuneDTO::getIdDimensiune)
                    .toArray(Long[]::new);

            throwIfAnyNumberLessThanZero(arrayIndex);

            Set<DimensiunePizza> dimensiunePizzaSet = dimensiunePizzaRepository.getAllDimensiuniByParamList(arrayIndex);

            if (dimensiunePizzaSet.size() != dimensiuneDTOSet.getCollection().size()) {

                List<Long> indexActual = dimensiunePizzaSet.stream()
                        .map(DimensiunePizza::getIdDimensiune)
                        .collect(Collectors.toCollection(ArrayList::new));

                List<Long> invalidIndex = Arrays.stream(arrayIndex).collect(Collectors.toCollection(ArrayList::new));
                invalidIndex.removeAll(indexActual);

                throw new NotFoundEntity("Indexurile: " + invalidIndex + " nu au fost gasite in baza de date");
            }

            dimensiunePizzaSet.forEach(i -> i.getPizzaSet().add(pizza));
            pizza.getDimensiunePizzas().addAll(dimensiunePizzaSet);
        }

        pizzaRepository.save(pizza);
    }

    @Override
    public void adaugareTipuriBlatByPizzaId(Long id, CollectionSetWrapper<TipBlatDTO> tipuriBlatDTOSet) {
        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        if (tipuriBlatDTOSet.getCollection() == null) {
            throw new NullException("Collectia nu trebuie sa fie null");
        }

        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundEntity("Id-ul specificat nu se aflta in baza de date. Current id:[" + id + "]"));

        if (tipuriBlatDTOSet == null && tipuriBlatDTOSet.getCollection().isEmpty()) {
            throw new NullException("Lista trebuie sa contina elemente");
        } else {

            Long[] arrayIndex = tipuriBlatDTOSet.getCollection().stream()
                    .map(TipBlatDTO::getIdTipBlat)
                    .toArray(Long[]::new);

            throwIfAnyNumberLessThanZero(arrayIndex);

            Set<TipuriBlatPizza> tipuriBlatPizzas = tipuriblatPizzaRepository.getAllTipuriBlatByParamList(arrayIndex);

            if (tipuriBlatPizzas.size() != tipuriBlatDTOSet.getCollection().size()) {

                List<Long> indexActual = tipuriBlatPizzas.stream()
                        .map(TipuriBlatPizza::getIdTipBlat)
                        .collect(Collectors.toCollection(ArrayList::new));

                List<Long> invalidIndex = Arrays.stream(arrayIndex).collect(Collectors.toCollection(ArrayList::new));
                invalidIndex.removeAll(indexActual);

                throw new NotFoundEntity("Indexurile: " + invalidIndex + " nu au fost gasite in baza de date");
            }

            tipuriBlatPizzas.forEach(i -> i.getPizzas().add(pizza));
            pizza.getTipuriBlatPizzas().addAll(tipuriBlatPizzas);
        }

        pizzaRepository.save(pizza);
    }

    @Override
    public void adaugareSosuriByPizzaId(Long id, CollectionSetWrapper<SosBlatDTO> sosBlatDTOSet) {
        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        if (sosBlatDTOSet.getCollection() == null) {
            throw new NullException("Collectia nu trebuie sa fie null");
        }

        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundEntity("Id-ul specificat nu se aflta in baza de date. Current id:[" + id + "]"));

        if (sosBlatDTOSet == null && sosBlatDTOSet.getCollection().isEmpty()) {
            throw new NullException("Lista trebuie sa contina elemente");
        } else {

            Long[] arrayIndex = sosBlatDTOSet.getCollection().stream()
                    .map(SosBlatDTO::getIdSos)
                    .toArray(Long[]::new);

            throwIfAnyNumberLessThanZero(arrayIndex);

            Set<SosBlatPizza> sosBlatPizzas = sosuriBlatPizzaRepository.getAllSosBlaturiByParamList(arrayIndex);

            if (sosBlatPizzas.size() != sosBlatDTOSet.getCollection().size()) {

                List<Long> indexActual = sosBlatPizzas.stream()
                        .map(SosBlatPizza::getIdSos)
                        .collect(Collectors.toCollection(ArrayList::new));

                List<Long> invalidIndex = Arrays.stream(arrayIndex).collect(Collectors.toCollection(ArrayList::new));
                invalidIndex.removeAll(indexActual);

                throw new NotFoundEntity("Indexurile: " + invalidIndex + " nu au fost gasite in baza de date");
            }

            sosBlatPizzas.forEach(i -> i.getPizzas().add(pizza));
            pizza.getSosBlatPizzas().addAll(sosBlatPizzas);
        }

        pizzaRepository.save(pizza);
    }

    @Override
    @Transactional
    public void deleteToppinguriByPizzaId(Long id, CollectionSetWrapper<TopingDTO> topingDTOSet) {
        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        if (topingDTOSet.getCollection() == null) {
            throw new NullException("Collectia nu trebuie sa fie null");
        }

        Long[] arrayIndex = topingDTOSet.getCollection().stream()
                .map(TopingDTO::getIdToping)
                .toArray(Long[]::new);

        throwIfAnyNumberLessThanZero(arrayIndex);
        pizzaRepository.deleteAllTopingsByPizzaID(id, arrayIndex);
    }

    @Override
    @Transactional
    public void deleteIngredienteByPizzaId(Long id, CollectionSetWrapper<IngredientDTO> ingredienteDTOSet) {

        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        if (ingredienteDTOSet.getCollection() == null) {
            throw new NullException("Collectia nu trebuie sa fie null");
        }

        Long[] arrayIndex = ingredienteDTOSet.getCollection().stream()
                .map(IngredientDTO::getIdIngredient)
                .toArray(Long[]::new);

        throwIfAnyNumberLessThanZero(arrayIndex);
        pizzaRepository.deleteAllIngredientsByPizzaID(id, arrayIndex);
    }

    @Override
    @Transactional
    public void deleteDimensinueByPizzaId(Long id, CollectionSetWrapper<DimensiuneDTO> dimensiuneDTOSet) {

        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        if (dimensiuneDTOSet.getCollection() == null) {
            throw new NullException("Collectia nu trebuie sa fie null");
        }

        Long[] arrayIndex = dimensiuneDTOSet.getCollection().stream()
                .map(DimensiuneDTO::getIdDimensiune)
                .toArray(Long[]::new);

        throwIfAnyNumberLessThanZero(arrayIndex);
        pizzaRepository.deleteAllDimensiuneByPizzaID(id, arrayIndex);
    }

    @Override
    @Transactional
    public void deleteTipuriBlatByPizzaId(Long id, CollectionSetWrapper<TipBlatDTO> tipuriBlatDTOSet) {

        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        if (tipuriBlatDTOSet.getCollection() == null) {
            throw new NullException("Collectia nu trebuie sa fie null");
        }

        Long[] arrayIndex = tipuriBlatDTOSet.getCollection().stream()
                .map(TipBlatDTO::getIdTipBlat)
                .toArray(Long[]::new);

        throwIfAnyNumberLessThanZero(arrayIndex);
        pizzaRepository.deleteAllTipBlatByPizzaID(id, arrayIndex);
    }

    @Override
    @Transactional
    public void deleteSosuriByPizzaId(Long id, CollectionSetWrapper<SosBlatDTO> sosBlatDTOSet) {
        if (id < 0) {
            throw new InvalidParameter("Id should be positive, current id:[" + id + "]");
        }

        if (sosBlatDTOSet.getCollection() == null) {
            throw new NullException("Collectia nu trebuie sa fie null");
        }

        Long[] arrayIndex = sosBlatDTOSet.getCollection().stream()
                .map(SosBlatDTO::getIdSos)
                .toArray(Long[]::new);


        throwIfAnyNumberLessThanZero(arrayIndex);
        pizzaRepository.deleteAllSosusriByPizzaID(id, arrayIndex);
    }

    /**
     * Functia verifica daca in colectia respectiva este un index < 0
     *
     * @param array - Parametrul de intrare reprezentand collectia de elemente index a bazei de date neprelucrata
     * @throws NotFoundEntity - arunca exceptia daca exista un element din collectia array care nu indeplineste conditia
     *                        stabilita
     */
    private void throwIfAnyNumberLessThanZero(Long[] array) throws NotFoundEntity {
        Arrays.stream(array)
                .filter(i -> i < 0)
                .findAny()
                .ifPresent(i -> {
                    throw new NotFoundEntity("Dimesiuni index shoudn't be negative; index found in collection: [" + i + "]");
                });

    }
}
