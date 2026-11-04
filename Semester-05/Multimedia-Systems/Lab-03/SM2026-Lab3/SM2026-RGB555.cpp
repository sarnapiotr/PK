#include "SM2026-RGB555.h"

#include <SDL2/SDL.h>

#include "SM2026-Lab3.h"

void setRGB555(int xx, int yy, Uint8 r, Uint8 g, Uint8 b) {
  Uint8 r8b = r << 3;
  Uint8 g8b = g << 3;
  Uint8 b8b = b << 3;

  setPixel(xx, yy, r8b, g8b, b8b);
}

void setRGB555(int xx, int yy, Uint16 rgb555) {
  Uint8 r5b = (rgb555 >> 10) & 0x1F;
  Uint8 g5b = (rgb555 >> 5) & 0x1F;
  Uint8 b5b = rgb555 & 0x1F;

  Uint8 r8b = r5b << 3;
  Uint8 g8b = g5b << 3;
  Uint8 b8b = b5b << 3;

  setPixel(xx, yy, r8b, g8b, b8b);
}

SDL_Color getRGB555(int xx, int yy) {
  SDL_Color color = getPixel(xx, yy);

  Uint8 r8b = color.r;
  Uint8 g8b = color.g;
  Uint8 b8b = color.b;

  Uint8 r5b = r8b >> 3;
  Uint8 g5b = g8b >> 3;
  Uint8 b5b = b8b >> 3;

  SDL_Color color555;
  color555.r = r5b;
  color555.g = g5b;
  color555.b = b5b;

  return color555;
}

Uint16 getRGB555_(int xx, int yy) {
  SDL_Color color = getPixel(xx, yy);

  Uint8 r8b = color.r;
  Uint8 g8b = color.g;
  Uint8 b8b = color.b;

  Uint8 r5b = r8b >> 3;
  Uint8 g5b = g8b >> 3;
  Uint8 b5b = b8b >> 3;

  Uint16 rgb555 = 0;
  rgb555 |= r5b << 10;
  rgb555 |= g5b << 5;
  rgb555 |= b5b;

  return rgb555;
}