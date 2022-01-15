package com.laioffer.jupiter.controller;

import com.laioffer.jupiter.entity.db.Item;
import com.laioffer.jupiter.service.GameService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

  @Autowired
  private GameService gameService;

  @RequestMapping(value = "/search", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, List<Item>> search(@RequestParam(value = "game_id") String gameId) {
    return gameService.searchItems(gameId);
  }

}
