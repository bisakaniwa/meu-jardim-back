package br.com.meujardim.service;

import br.com.meujardim.exception.OperacaoRestritaException;
import br.com.meujardim.exception.PlantaNotFoundException;
import br.com.meujardim.model.Planta;
import br.com.meujardim.repository.PlantaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNullElse;

@Service
public class PlantaService {

    private final PlantaRepository plantaRepository;

    private final JardimUserService jardimUserService;

    public PlantaService(PlantaRepository plantaRepository, JardimUserService jardimUserService) {
        this.plantaRepository = plantaRepository;
        this.jardimUserService = jardimUserService;
    }

    public List<Planta> buscarTodasAsPlantas() {
        List<Planta> listaPlantas = plantaRepository.findAll();
        if (listaPlantas.isEmpty()) {
            throw new PlantaNotFoundException("Nenhuma planta encontrada.");
        } else {
            return listaPlantas;
        }
    }

    @Transactional
    public Planta cadastrarPlanta(Planta planta, long userId) {
        boolean podeCadastrar = jardimUserService.verificaSerAdmin(userId);
        if (!podeCadastrar) {
            throw new OperacaoRestritaException("Operação restrita a administradores.");
        } else {
            return plantaRepository.save(planta);
        }
    }

    @Transactional
    public Planta editarPlanta(Planta plantaEditada, long userId) {
        Optional<Planta> procuraPlanta = plantaRepository.findById(plantaEditada.getPlantaId());
        boolean podeEditar = jardimUserService.verificaSerAdmin(userId);

        if (procuraPlanta.isEmpty()) {
            throw new PlantaNotFoundException("Planta não encontrada.");
        } else if (!podeEditar) {
            throw new OperacaoRestritaException("Operação restrita a administradores.");
        } else {
            Planta plantaEncontrada = procuraPlanta.get();
            plantaEncontrada.setNome(requireNonNullElse(plantaEditada.getNome(), plantaEncontrada.getNome()));
            plantaEncontrada.setNomeCientifico(requireNonNullElse(plantaEditada.getNomeCientifico(),
                    plantaEncontrada.getNomeCientifico()));
            plantaEncontrada.setTipoDePlanta(requireNonNullElse(plantaEditada.getTipoDePlanta(),
                    plantaEncontrada.getTipoDePlanta()));
            plantaEncontrada.setImagemReferencia(requireNonNullElse(plantaEditada.getImagemReferencia(),
                    plantaEncontrada.getImagemReferencia()));
            plantaEncontrada.setDescricao(requireNonNullElse(plantaEditada.getDescricao(), plantaEncontrada.getDescricao()));
            return plantaRepository.save(plantaEncontrada);
        }
    }

    @Transactional
    public void excluirPlanta(long plantaId, long userId) {
        Optional<Planta> procurarPlanta = plantaRepository.findById(plantaId);
        boolean podeExcluir = jardimUserService.verificaSerAdmin(userId);

        if (procurarPlanta.isEmpty()) {
            throw new PlantaNotFoundException("Planta não encontrada.");
        } else if (!podeExcluir) {
            throw new OperacaoRestritaException("Operação restrita a administradores.");
        } else {
            plantaRepository.deleteById(plantaId);
        }
    }
}
