package pl.ymz.udemystart.lang;

import java.util.List;

import static java.util.stream.Collectors.toList;

class LangService {
    private LangRepository repository;

    LangService() {
        this(new LangRepository());
    }

    LangService(LangRepository repository) {
        this.repository = repository;
    }

    List<LangDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(lang -> new LangDTO(lang))
                .collect(toList());
    }


}
