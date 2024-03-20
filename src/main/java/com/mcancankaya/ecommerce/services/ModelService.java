package com.mcancankaya.ecommerce.services;

import com.mcancankaya.ecommerce.core.mapper.ModelMapperService;
import com.mcancankaya.ecommerce.core.response.CustomResponse;
import com.mcancankaya.ecommerce.entities.Model;
import com.mcancankaya.ecommerce.repositories.ModelRepository;
import com.mcancankaya.ecommerce.services.dtos.request.model.CreateModelRequest;
import com.mcancankaya.ecommerce.services.dtos.request.model.UpdateModelRequest;
import com.mcancankaya.ecommerce.services.dtos.response.ModelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapperService modelMapperService;

    public CustomResponse<ModelResponse> create(CreateModelRequest request) {
        Model model = modelMapperService.forRequest().map(request, Model.class);
        modelRepository.save(model);
        System.out.println(model.getBrand() + "." + model.getName() + "." + model.getId());
        return null;
    }

    public CustomResponse<ModelResponse> update(UpdateModelRequest request) {
        Model model = modelMapperService.forRequest().map(request, Model.class);
        Model updatedModel = modelRepository.save(model);
        ModelResponse response = modelMapperService.forResponse().map(updatedModel, ModelResponse.class);
        return new CustomResponse<>(response, "Model updated.");
    }

    public CustomResponse<?> deleteById(Integer id) {
        modelRepository.deleteById(id);
        return new CustomResponse<>("Model deleted.");
    }

    public CustomResponse<List<ModelResponse>> getAll() {
        List<Model> models = modelRepository.findAll();
        List<ModelResponse> modelResponses = models.stream().map((model) -> modelMapperService.forResponse().map(model, ModelResponse.class)).toList();
        return new CustomResponse<>(modelResponses, "Model Listed.");
    }

    public CustomResponse<ModelResponse> getById(Integer id) {
        ModelResponse modelResponse = modelMapperService.forRequest().map(modelRepository.findById(id), ModelResponse.class);
        return new CustomResponse<>(modelResponse, "Model listed.");
    }
}
