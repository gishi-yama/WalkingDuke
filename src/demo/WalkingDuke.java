package demo;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class WalkingDuke extends Application {

  // マウスカーソルのx座標(double値）を管理するプロパティ
  private SimpleDoubleProperty cursorXProp;
  // マウスカーソルのy座標(double値）を管理するプロパティ
  private SimpleDoubleProperty cursorYProp;

  public WalkingDuke() {
    // クラスがインスタンス化されるときに、プロパティを初期化
    cursorXProp = new SimpleDoubleProperty(0);
    cursorYProp = new SimpleDoubleProperty(0);
  }

  @Override
  public void start(Stage primaryStage) {

    Label cursorXLabel = new Label();
//    ラベルで cursorXProp, cursoryProp の値を表示する。
//    プロパティが文字列ではなくDouble値（数値）なので、bindBidirectionaltextPropertyで数値を文字列に変化させてひもづける
    cursorXLabel.textProperty().bindBidirectional(cursorXProp, new NumberStringConverter());
    Label cursorYLabel = new Label();
    cursorYLabel.textProperty().bindBidirectional(cursorYProp, new NumberStringConverter());

//    ラベルの表示部
    HBox cursorBox = new HBox();
    cursorBox.setPadding(new Insets(10));
    cursorBox.getChildren().addAll(new Label("カーソル : "), cursorXLabel, new Label(" , "), cursorYLabel);

//    Dukeの表示部
    ImageView iv = new ImageView(new Image("/demo/duke.png"));
//    Dukeのx座標を、cursorXPropの値とひもづける
    iv.xProperty().bind(cursorXProp);
//    Dukeのy座標を100に固定する。
//    iv.yProperty().bind(cursorYProp) に変えると、Dukeのy座標がcursorYPropに紐付いて、マウスにDukeがついてきます
    iv.setY(100);

    Group root = new Group();
    root.getChildren().addAll(cursorBox, iv);

//    シーン（画面）の表示部
    Scene scene = new Scene(root, 300, 250);
    scene.setOnMouseMoved(e -> {
//      マウスが移動したら、cursorXProp, cursorYPropの値をマウスカーソルのx,y座標で更新する
      cursorXProp.setValue(e.getSceneX());
      cursorYProp.setValue(e.getSceneX());
    });

    primaryStage.setTitle("Dukeがマウスについて歩くよ！");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
