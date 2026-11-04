#include "SM2026-RGB565.h"

#include <SDL2/SDL.h>

#include "SM2026-Lab3.h"

void setRGB565(int xx, int yy, Uint8 r, Uint8 g, Uint8 b) {
  Uint8 r8b = r << 3;
  Uint8 g8b = g << 2;
  Uint8 b8b = b << 3;

  setPixel(xx, yy, r8b, g8b, b8b);
}

void setRGB565(int xx, int yy, Uint16 rgb565) {
  Uint8 r5b = (rgb565 >> 11) & 0x1F;
  Uint8 g6b = (rgb565 >> 5) & 0x3F;
  Uint8 b5b = rgb565 & 0x1F;

  Uint8 r8b = r5b << 3;
  Uint8 g8b = g6b << 2;
  Uint8 b8b = b5b << 3;

  setPixel(xx, yy, r8b, g8b, b8b);
}

SDL_Color getRGB565(int xx, int yy) {
  SDL_Color color = getPixel(xx, yy);

  Uint8 r8b = color.r;
  Uint8 g8b = color.g;
  Uint8 b8b = color.b;

  Uint8 r5b = r8b >> 3;
  Uint8 g6b = g8b >> 2;
  Uint8 b5b = b8b >> 3;

  SDL_Color color565;
  color565.r = r5b;
  color565.g = g6b;
  color565.b = b5b;

  return color565;
}

Uint16 getRGB565_(int xx, int yy) {
  SDL_Color color = getPixel(xx, yy);

  Uint8 r8b = color.r;
  Uint8 g8b = color.g;
  Uint8 b8b = color.b;

  Uint8 r5b = r8b >> 3;
  Uint8 g6b = g8b >> 2;
  Uint8 b5b = b8b >> 3;

  Uint16 rgb565 = 0;
  rgb565 |= r5b << 11;
  rgb565 |= g6b << 5;
  rgb565 |= b5b;

  return rgb565;
}