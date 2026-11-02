#include "SM2026-YUV.h"

#include <SDL2/SDL.h>

#include "SM2026-Lab1.h"

void setYUV(int xx, int yy, float y, float u, float v) {
  float r = y + 1.13983f * v;
  float g = y - 0.39465f * u - 0.58060f * v;
  float b = y + 2.03211f * u;

  Uint8 rNorm = normalizeUint8(r);
  Uint8 gNorm = normalizeUint8(g);
  Uint8 bNorm = normalizeUint8(b);

  setPixel(xx, yy, rNorm, gNorm, bNorm);
}

YUV getYUV(int xx, int yy) {
  SDL_Color color = getPixel(xx, yy);

  Uint8 r = color.r;
  Uint8 g = color.g;
  Uint8 b = color.b;

  float y = 0.299f * r + 0.587f * g + 0.114f * b;
  float u = -0.14713f * r - 0.28886f * g + 0.436f * b;
  float v = 0.615f * r - 0.51499f * g - 0.10001f * b;

  return YUV(y, u, v);
}